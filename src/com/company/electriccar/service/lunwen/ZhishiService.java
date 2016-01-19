package com.company.electriccar.service.lunwen;

import com.company.electriccar.domain.TECH_ZHISHI;
import com.company.modules.dao.BaseDao;
import com.company.modules.dao.SqlParameter;
import com.company.modules.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Created by zxl on 14-6-22.
 */
@Service
public class ZhishiService {
    @Autowired
    private BaseDao baseDao;
    public void add(TECH_ZHISHI user) {
        if (StringUtil.isBlank(user.getId())) {

        user.setCreate_time(new Date().getTime());
        }
        user.insertOrUpdate();
    }

    public Map selectByPk(String id) {
        return
        baseDao.queryForMap("select *,FROM_UNIXTIME(left( create_time,10), '%Y-%m-%d' )  as create_time_str  from TECH_ZHISHI where id ='" + id + "'");
    }

    public void deleteById(String id) {
        TECH_ZHISHI zhuanlan = new TECH_ZHISHI();
        zhuanlan.setId(id);
        zhuanlan.deleteById();
    }

    public Map find(TECH_ZHISHI zhuan, HttpServletRequest request) {
        StringBuffer buffer = new StringBuffer("select *,FROM_UNIXTIME(left( create_time,10), '%Y-%m-%d' ) as createTime from TECH_ZHISHI where 1=1 ");
        if (StringUtil.isNotBlank(zhuan.getTitle())) {
            buffer.append(" and title like '%").append(zhuan.getTitle()).append("%'");
        }
        buffer.append(" order by create_time desc");
        Map resultMap= baseDao.queryForDataGrid(request,buffer.toString(), new SqlParameter());
        return resultMap;
    }
}
