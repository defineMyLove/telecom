package com.company.electriccar.service.solution;

import com.company.electriccar.domain.FANGAN_FENLEI;
import com.company.modules.dao.BaseDao;
import com.company.modules.dao.SqlParameter;
import com.company.modules.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zxl on 14-6-22.
 */
@Service
public class SolutionClassifyService {
    @Autowired
    private BaseDao baseDao;
    public void add(FANGAN_FENLEI user) {
        if (StringUtil.isBlank(user.getId())) {

        user.setCreate_time(new Date().getTime());
        }
        user.insertOrUpdate();
    }

    public Map selectByPk(String id) {
        return
        baseDao.queryForMap("select *,FROM_UNIXTIME(left( create_time,10), '%Y-%m-%d' )  as create_time_str  from fangan_fenlei where id ='" + id + "'");
    }

    public void deleteById(String id) {
        FANGAN_FENLEI zhuanlan = new FANGAN_FENLEI();
        zhuanlan.setId(id);
        zhuanlan.deleteById();
    }

    public Map find(FANGAN_FENLEI zhuan, HttpServletRequest request) {
        StringBuffer buffer = new StringBuffer("select * from fangan_fenlei where 1=1 ");
        if (StringUtil.isNotBlank(zhuan.getName())) {
            buffer.append(" and name like '%").append(zhuan.getName()).append("%'");
        }
        buffer.append(" order by order_no,create_time desc");

        Map resultMap= baseDao.queryForDataGrid(request,buffer.toString(), new SqlParameter());
        return resultMap;
    }

    public List<Map> getAll(String id) {
        return baseDao.queryForList("select * from fangan_fenlei  order by order_no,create_time desc");
    }

    public List<Map> find(FANGAN_FENLEI fangan, int i) {
        StringBuffer buffer = new StringBuffer("select * from fangan_fenlei where 1=1 ");
        if (StringUtil.isNotBlank(fangan.getName())) {
            buffer.append(" and name like '%").append(fangan.getName()).append("%'");
        }
        buffer.append(" order by order_no,create_time desc");
        Map resultMap= baseDao.queryForDataGrid(1,10, buffer.toString(), new SqlParameter());
        return (List<Map>) resultMap.get("rows");
    }

    public List<Map> findAll() {
        StringBuffer buffer = new StringBuffer("select * from fangan_fenlei where 1=1 ");
        buffer.append(" order by order_no,create_time desc");
        return baseDao.queryForList(buffer.toString());

    }
}
