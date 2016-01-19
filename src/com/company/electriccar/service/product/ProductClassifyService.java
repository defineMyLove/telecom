package com.company.electriccar.service.product;

import com.company.electriccar.domain.CHANPIN_FENLEI;
import com.company.modules.dao.BaseDao;
import com.company.modules.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zxl on 14-6-22.
 */
@Service
public class ProductClassifyService {
    @Autowired
    private BaseDao baseDao;
    public List<Map> getAll() {
        return baseDao.queryForList("select * from CHANPIN_FENLEI order by order_no,create_time desc");
    }

    public CHANPIN_FENLEI selectByPK(String id) {
        CHANPIN_FENLEI fenlei = new CHANPIN_FENLEI();
        fenlei.setId(id);
        return fenlei.queryForBean();
    }

    public void add(CHANPIN_FENLEI xzqh) {
        xzqh.setCreate_time(new Date().getTime());
        xzqh.setId(StringUtil.getUUID());
        xzqh.insert();
    }

    public Boolean isNoChildren(String code) {
        int count = baseDao.queryForInteger("select count(*) from CHANPIN_FENLEI where up_id ='" + code + "'");
        if (count > 0) {
            return false;
        }
        return true;
    }

    public void delete(String code) {
        CHANPIN_FENLEI fenlei = new CHANPIN_FENLEI();
        fenlei.setId(code);
        fenlei.deleteById();
    }

    public void updateByPk(CHANPIN_FENLEI xzqh) {
        xzqh.insertOrUpdate();
    }

    public Map selectByPk(String fenlei_id) {
        return baseDao.queryForMap("select * from CHANPIN_FENLEI where id ='" + fenlei_id + "'");
    }

    public List<Map> getLevelClassify() {
        return baseDao.queryForList("select * from CHANPIN_FENLEI where up_id =0 order by order_no,create_time desc");
    }
}
