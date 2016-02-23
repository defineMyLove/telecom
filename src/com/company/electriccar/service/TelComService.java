package com.company.electriccar.service;

import com.company.electriccar.domain.CUS_INFO;
import com.company.electriccar.domain.SALEMAN;
import com.company.modules.dao.BaseDao;
import com.company.modules.dao.SqlParameter;
import com.company.modules.utils.StringUtil;
import com.company.modules.web.ServiceResponse;
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
public class TelComService {
    @Autowired
    private BaseDao baseDao;


    public List<Map> productList() {
        StringBuffer buffer = new StringBuffer("select *,FROM_UNIXTIME(left( create_time,10), '%Y-%m-%d' ) as createTime from fangan_fenlei where 1=1 ");
        buffer.append(" order by order_no asc");
        List<Map> mapList = baseDao.queryForList(buffer.toString());
        for (Map map : mapList) {
            buffer = new StringBuffer("select *,FROM_UNIXTIME(left( create_time,10), '%Y-%m-%d' ) as createTime from fangan_info where fenlei_id='" + map.get("id") + "'");
            List<Map> tempList = baseDao.queryForList(buffer.toString());
            map.put("infoList", tempList);
        }
        return mapList;
    }

    public Map selectByPk(String id) {
        StringBuffer buffer = new StringBuffer("select *,FROM_UNIXTIME(left( create_time,10), '%Y-%m-%d' ) as createTime from fangan_info where id= '" + id + "'");
        return baseDao.queryForMap(buffer.toString());
    }

    public List<Map> queryList() {
        StringBuffer buffer = new StringBuffer("select id,name,pic_path,note,FROM_UNIXTIME(left( create_time,10), '%Y-%m-%d' ) as createTime from fangan_info limit 0,10");
        return baseDao.queryForList(buffer.toString());
    }


    public List<Map> queryTelList() {
        StringBuffer buffer = new StringBuffer("select * from service_tel");
        return baseDao.queryForList(buffer.toString());
    }
}
