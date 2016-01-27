package com.company.electriccar.service;

import com.company.electriccar.common.syscontext.Const;
import com.company.electriccar.domain.CUS_CARD_INFO;
import com.company.electriccar.domain.CUS_INFO;
import com.company.electriccar.domain.CUS_VICE_CARD_INFO;
import com.company.modules.dao.BaseDao;
import com.company.modules.dao.SqlParameter;
import com.company.modules.utils.JsonUtil;
import com.company.modules.utils.StringUtil;
import com.company.modules.web.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zxl on 14-6-22.
 */
@Service
public class CusInfoService {
    @Autowired
    private BaseDao baseDao;

    public ServiceResponse add(CUS_INFO user) {
        ServiceResponse serviceResponse = new ServiceResponse();
        if (StringUtil.isNotBlank(user.getSale_id())) {    //如果有编号则验证编号是否存在
            int count = baseDao.queryForInteger("select count(*) from saleman where card_no =" + user.getSale_id());
            if (count == 0) {
                serviceResponse.setingError("发展人编号不存在");
            }
        }
        if (StringUtil.isBlank(user.getId())) {
            user.setCreate_time(new Date().getTime());
        }
        serviceResponse.setingSucess("谢谢您的合作，我们会尽快联系您。");
        user.insertOrUpdate();
        //添加套餐信息(目前只有电话卡ID，之后其他套餐的话需要判断套餐类型)
        CUS_CARD_INFO cusCardInfo = new CUS_CARD_INFO();
        if (StringUtil.isNotBlank(user.getChanpin_id())) {
            Map<String, String> productMap = baseDao.queryForMap("select name,order_no from fangan_info where id ='" + user.getChanpin_id() + "'");
            if (productMap != null) {
                cusCardInfo.setProduct_name(productMap.get("name"));
                cusCardInfo.setProduct_order_no(productMap.get("order"));
            }
            cusCardInfo.setProduct_id(user.getChanpin_id());
        }
        cusCardInfo.setCus_id(user.getId());
        cusCardInfo.setState(Const.CUS_STATE_NEW);
        if (StringUtil.isNotBlank(user.getSale_id())) {
            cusCardInfo.setSale_id(user.getSale_id());
        }
        if (StringUtil.isNotBlank(user.getChanpin_id())) {
            cusCardInfo.setProduct_order_no(user.getChanpin_id());
        }
        cusCardInfo.setCreate_time(new Date().getTime());
        cusCardInfo.setProduct_type(Const.PRODUCT_TYPE_0); //目前写死了
        cusCardInfo.insert();
        return serviceResponse;
    }

    /**
     * 查询客户信息，不包括客户的套餐信息
     *
     * @param id
     * @return
     */
    public Map selectByPk(String id) {
        return
                baseDao.queryForMap("select *,FROM_UNIXTIME(left( create_time,10), '%Y-%m-%d' )  as create_time_str  from cus_info where id ='" + id + "'");
    }

    /**
     * 查询客户信息，包括客户的套餐信息
     *
     * @param id
     * @return
     */
    public Map selectAllByPk(String id) {
        return
                baseDao.queryForMap("select a.*,FROM_UNIXTIME(left( a.create_time,10), '%Y-%m-%d' )  as create_time_str,b.* from cus_info a left join cus_card_info b on a.id = b.cus_id and id ='" + id + "'");
    }

    public ServiceResponse deleteById(String id) {
        ServiceResponse response = new ServiceResponse();
        response.setResult(true);
        response.setMsg("删除成功");
        CUS_INFO zhuanlan = new CUS_INFO();
        zhuanlan.setId(id);
        zhuanlan.deleteById();
        return response;
    }

    public Map find(CUS_INFO zhuan, HttpServletRequest request) {
        String prductName = request.getParameter("product_name");
        String saleNo = request.getParameter("sale_no");
        String saleName = request.getParameter("sale_name");
        String productId = request.getParameter("product_id");
        StringBuffer buffer = new StringBuffer("SELECT\n" +
                "\tb.*, d.id AS product_id,\n" +
                "\td.createTimeStr,\n" +
                "\td.productCreateTime,\n" +
                "\td.sale_name,\n" +
                "\td.card_state,\n" +
                "\td.product_order_no,\n" +
                "\td.product_type,\n" +
                "  d.product_no,\n" +
                "\td.product_name FROM\n" +
                "\tcus_info b, ");
        if (StringUtil.isNotBlank(saleNo) || StringUtil.isNotBlank(saleName)) {
            buffer.append("cus_sale_man");
        } else {
            buffer.append("cus_car_sale_info");
        }
        buffer.append(" d where d.cus_id = b.id");
        if (StringUtil.isNotBlank(zhuan.getCus_name())) {
            buffer.append(" and b.cus_name like '%" + zhuan.getCus_name() + "%'");
        }
        if (StringUtil.isNotBlank(zhuan.getCus_address())) {
            buffer.append(" and b.cus_address like '%" + zhuan.getCus_address() + "%'");
        }
        if (StringUtil.isNotBlank(zhuan.getCus_card_id())) {
            buffer.append(" and b.cus_card_id like '%" + zhuan.getCus_card_id() + "%'");
        }
        if (StringUtil.isNotBlank(zhuan.getCus_tel())) {
            buffer.append(" and b.cus_tel like '%" + zhuan.getCus_tel() + "%'");
        }
        if (zhuan.getState() != null && !zhuan.getState().equals(-1)) {
            buffer.append(" and d.card_state = " + zhuan.getState());
        }
        if (StringUtil.isNotBlank(prductName)) {
            buffer.append(" and d.product_name like '%" + prductName + "%'");
        }
        if (StringUtil.isNotBlank(saleName)) {
            buffer.append(" and d.sale_name like '%" + saleName + "%'");
        }
        if (StringUtil.isNotBlank(saleNo)) {
            buffer.append(" and d.sale_id like '%" + saleNo + "%'");
        }
        if (StringUtil.isNotBlank(productId)) {
            buffer.append(" and d.productId ='" + saleNo + "'");
        }
        buffer.append(" order by productCreateTime desc");
        Map resultMap = baseDao.queryForDataGrid(request, buffer.toString(), new SqlParameter());
        return resultMap;
    }

    /**
     * 通过id联合查询出套餐信息
     *
     * @param id 套餐实例ID
     * @return
     */
    public Map find(String id) {
        StringBuffer buffer = new StringBuffer("SELECT\n" +
                "\tb.*, d.id AS product_id,\n" +
                "\td.createTimeStr,\n" +
                "\td.productCreateTime,\n" +
                "\td.sale_name,\n" +
                "\td.card_state,\n" +
                "\td.product_order_no,\n" +
                "\td.product_type,\n" +
                "\td.serial_num,\n" +
                "\td.route_id,\n" +
                "\td.broadband_id,\n" +
                "\td.expire_time,\n" +
                "\td.banliTimeStr,\n" +
                "\td.createTimeStr,\n" +
                "  d.product_no,\n" +
                "\td.product_name FROM\n" +
                "\tcus_info b,cus_car_sale_info d where d.cus_id = b.id");

        if (StringUtil.isNotBlank(id)) {
            buffer.append(" and d.id ='" + id + "'");
        }
        buffer.append(" order by productCreateTime desc");
        Map resultMap = baseDao.queryForMap(buffer.toString(), new SqlParameter());
        return resultMap;
    }

    /**
     * 修改状态
     *
     * @param id
     * @return
     */
    public ServiceResponse updateState(String id, int state) {
        ServiceResponse response = new ServiceResponse();
        response.setResult(true);
        response.setMsg("操作成功");
        CUS_CARD_INFO zhuanlan = new CUS_CARD_INFO();
        zhuanlan.setId(id);
        zhuanlan.setState(state);
        zhuanlan.update();
        return response;
    }

    /**
     * 保存套餐办理信息(主卡、背卡)并修改套餐数据,修改状态为已办理
     *
     * @param vice_card_str
     * @param card_info
     * @return
     */
    public ServiceResponse updateProduct(String vice_card_str, CUS_CARD_INFO card_info) {
        ServiceResponse response = new ServiceResponse();
        response.setResult(true);
        response.setMsg("操作成功");
        card_info.setBanli_time(new Date().getTime());
        card_info.setState(Const.CUS_STATE_BANLI);
        card_info.insertOrUpdate();

        //副卡信息(先删除后添加)
        baseDao.execute("delete from cus_vice_card_info where main_card_id='" + card_info.getId() + "'", null);
        List<Map> viceCardInfos = JsonUtil.json2List(vice_card_str);
        long time = new Date().getTime();
        for (Map<String, String> map : viceCardInfos) {
            CUS_VICE_CARD_INFO temp = new CUS_VICE_CARD_INFO();
            temp.setVice_card_id(map.get("vice_card_id"));
            temp.setBack_card_id(map.get("back_card_id"));
            temp.setCreate_time(time);
            temp.setCus_id(card_info.getCus_id());
            temp.setMain_card_id(card_info.getId());
            temp.insert();
        }
        return response;
    }

    public void updateCusInfo(CUS_INFO user) {
        user.update();
    }
}
