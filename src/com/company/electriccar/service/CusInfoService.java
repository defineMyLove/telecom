package com.company.electriccar.service;

import com.company.electriccar.common.syscontext.Const;
import com.company.electriccar.domain.CUS_INFO;
import com.company.modules.dao.BaseDao;
import com.company.modules.dao.SqlParameter;
import com.company.modules.utils.StringUtil;
import com.company.modules.web.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
        //如果有编号则验证编号是否存在
        if (StringUtil.isNotBlank(user.getSale_id())) {
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
        return serviceResponse;
    }

    /**
     * 查询客户信息，不包括客户的套餐信息
     * @param id
     * @return
     */
    public Map selectByPk(String id) {
        return
                baseDao.queryForMap("select *,FROM_UNIXTIME(left( create_time,10), '%Y-%m-%d' )  as create_time_str  from CUS_INFO where id ='" + id + "'");
    }

    /**
     * 查询客户信息，包括客户的套餐信息
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
        StringBuffer buffer = new StringBuffer("select a.*,FROM_UNIXTIME(left( a.create_time,10), '%Y-%m-%d' ) as createTime,b.name as sale_name from CUS_INFO a, saleman b where a.sale_id = b.card_no");
        if (StringUtil.isNotBlank(zhuan.getCus_name())) {
            buffer.append(" and cus_name like '%" + zhuan.getCus_name() + "%'");
        }
        if (StringUtil.isNotBlank(zhuan.getCus_address())) {
            buffer.append(" and cus_address like '%" + zhuan.getCus_address() + "%'");
        }
        if (StringUtil.isNotBlank(zhuan.getCus_card_id())) {
            buffer.append(" and cus_card_id like '%" + zhuan.getCus_card_id() + "%'");
        }
        if (StringUtil.isNotBlank(zhuan.getCus_tel())) {
            buffer.append(" and cus_tel like '%" + zhuan.getCus_tel() + "%'");
        }
        if (zhuan.getState()!=null) {
            buffer.append(" and state = " + zhuan.getState());
        }
        buffer.append(" order by create_time desc");
        Map resultMap = baseDao.queryForDataGrid(request, buffer.toString(), new SqlParameter());
        return resultMap;
    }

    /**
     * 修改状态
     * @param id
     * @return
     */
    public ServiceResponse updateState(String id,int state) {
        ServiceResponse response = new ServiceResponse();
        response.setResult(true);
        response.setMsg("操作成功");
        CUS_INFO zhuanlan = new CUS_INFO();
        zhuanlan.setId(id);
        zhuanlan.setState(state);
        zhuanlan.update();
        return response;
    }
}
