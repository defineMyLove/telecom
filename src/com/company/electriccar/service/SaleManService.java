package com.company.electriccar.service;

import com.company.electriccar.domain.SALEMAN;
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
public class SaleManService {
    @Autowired
    private BaseDao baseDao;

    public void add(SALEMAN user) {
        if (StringUtil.isBlank(user.getId())) {
            user.setCreate_time(new Date().getTime());
            String cardNo = baseDao.queryForString("SELECT nextval('sequence');");
            user.setCard_no(cardNo);
        }
        user.insertOrUpdate();
    }

    public Map selectByPk(String id) {
        return
                baseDao.queryForMap("select *,FROM_UNIXTIME(left( create_time,10), '%Y-%m-%d' )  as create_time_str  from saleman where id ='" + id + "'");
    }

    public ServiceResponse deleteById(String id) {
        ServiceResponse response = new ServiceResponse();
        response.setResult(true);
        response.setMsg("删除成功");
        SALEMAN zhuanlan = new SALEMAN();
        zhuanlan.setId(id);
        zhuanlan.deleteById();
        return response;
    }

    public Map find(SALEMAN zhuan, HttpServletRequest request) {
        StringBuffer buffer = new StringBuffer("select *,FROM_UNIXTIME(left( create_time,10), '%Y-%m-%d' ) as createTime from saleman where 1=1 ");
        if (StringUtil.isNotBlank(zhuan.getName())) {
            buffer.append(" and name like '%" + zhuan.getName() + "%'");
        }
        if (StringUtil.isNotBlank(zhuan.getAddress())) {
            buffer.append(" and address like '%" + zhuan.getAddress() + "%'");
        }
        if (StringUtil.isNotBlank(zhuan.getCard_no())) {
            buffer.append(" and card_no like '%" + zhuan.getCard_no() + "%'");
        }
        if (StringUtil.isNotBlank(zhuan.getTel())) {
            buffer.append(" and tel like '%" + zhuan.getTel() + "%'");
        }
        buffer.append(" order by create_time desc");
        Map resultMap = baseDao.queryForDataGrid(request, buffer.toString(), new SqlParameter());
        return resultMap;
    }
}
