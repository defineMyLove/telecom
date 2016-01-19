package com.company.electriccar.service.reader;

import com.company.electriccar.domain.PRO_READER;
import com.company.electriccar.domain.TECH_ZHUANLAN;
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
public class ReaderService {
    @Autowired
    private BaseDao baseDao;

    public void add(PRO_READER user) {
        if (StringUtil.isBlank(user.getId())) {
           // user.setCreate_time(new Date().getTime());
        }
        user.insertOrUpdate();
    }

    public Map selectByPk(String id) {
        return
                baseDao.queryForMap("select *,FROM_UNIXTIME(left( create_time,10), '%Y-%m-%d' )  as create_time_str  from PRO_READER where id ='" + id + "'");
    }

    public void deleteById(String id) {
        PRO_READER zhuanlan = new PRO_READER();
        zhuanlan.setId(id);
        zhuanlan.deleteById();
    }

    public Map find(PRO_READER zhuan, HttpServletRequest request) {
        StringBuffer buffer = new StringBuffer("select *,FROM_UNIXTIME(left( create_time,10), '%Y-%m-%d' ) as createTime from PRO_READER where 1=1 ");
        if (StringUtil.isNotBlank(zhuan.getName())) {
            buffer.append(" and title like '%").append(zhuan.getName()).append("%'");
        }
        buffer.append(" order by create_time desc");
        Map resultMap = baseDao.queryForDataGrid(request, buffer.toString(), new SqlParameter());
        return resultMap;
    }
}
