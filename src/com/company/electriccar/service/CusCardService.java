package com.company.electriccar.service;

import com.company.electriccar.common.syscontext.Const;
import com.company.electriccar.domain.CUS_CARD_INFO;
import com.company.electriccar.domain.CUS_INFO;
import com.company.modules.dao.BaseDao;
import com.company.modules.dao.SqlParameter;
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
 * 快带办理服务类
 */
@Service
public class CusCardService {
    @Autowired
    private BaseDao baseDao;

    public ServiceResponse add(CUS_INFO user) {
        ServiceResponse serviceResponse = new ServiceResponse();
        if (StringUtil.isNotBlank(user.getSale_id())) {    //如果有编号则验证编号是否存在
            int count = baseDao.queryForInteger("select count(*) from saleman where card_no =" + user.getSale_id());
            if (count == 0) {
                serviceResponse.setMsg("发展人编号不存在");
            }
        }
        if (StringUtil.isBlank(user.getId())) {
            user.setCreate_time(new Date().getTime());
        }
        serviceResponse.setMsg("谢谢您的合作，我们会尽快联系您。");
        user.insertOrUpdate();
        //添加套餐信息(目前只有电话卡ID，之后其他套餐的话需要判断套餐类型)
        CUS_CARD_INFO cusCardInfo = new CUS_CARD_INFO();
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
                baseDao.queryForMap("select *,FROM_UNIXTIME(left( create_time,10), '%Y-%m-%d' )  as create_time_str  from CUS_CARD_INFO where id ='" + id + "'");
    }

    /**
     * 查询客户信息，包括客户的套餐信息
     *
     * @param id
     * @return
     */
    public Map selectAllByPk(String id) {
        return
                baseDao.queryForMap("select a.*,FROM_UNIXTIME(left( a.create_time,10), '%Y-%m-%d' )  as create_time_str,b.* from CUS_INFO a left join cus_card_info b on a.id = b.cus_id and id ='" + id + "'");
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
        StringBuffer buffer = new StringBuffer("select b.*,d.id as product_id,d.createTimeStr,d.productCreateTime,d.sale_name,d.card_state,d.product_order_no,d.product_type from cus_info b RIGHT JOIN (");
        buffer.append("select a.*,a.create_time as productCreateTime,FROM_UNIXTIME(left( a.create_time,10), '%Y-%m-%d' ) as createTimeStr,a.state as card_state,c.name as sale_name  from cus_card_info a left join saleman c on  a.sale_id = c.card_no) as d");
        buffer.append(" on d.cus_id = b.id ");
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
        if (zhuan.getState() != null) {
            buffer.append(" and card_state = " + zhuan.getState());
        }
        buffer.append(" order by productCreateTime desc");
        Map resultMap = baseDao.queryForDataGrid(request, buffer.toString(), new SqlParameter());
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
        CUS_INFO zhuanlan = new CUS_INFO();
        zhuanlan.setId(id);
        zhuanlan.setState(state);
        zhuanlan.update();
        return response;
    }

    /**
     * 查询副卡信息
     * @param id
     * @return
     */
    public List<Map> selectViceList(String id) {
        List<Map> resultMap = baseDao.queryForList("select * from cus_vice_card_info where main_card_id='" + id + "'");
        return resultMap == null ? new ArrayList<Map>():resultMap;
    }
}
