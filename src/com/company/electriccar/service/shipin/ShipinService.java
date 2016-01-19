package com.company.electriccar.service.shipin;

import com.company.electriccar.domain.SHIPIN_INFO;
import com.company.modules.dao.BaseDao;
import com.company.modules.dao.SqlParameter;
import com.company.modules.upload.UpLoadContext;
import com.company.modules.upload.UploadResource;
import com.company.modules.utils.Identities;
import com.company.modules.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Created by zxl on 14-6-22.
 */
@Service
public class ShipinService {
    @Autowired
    private BaseDao baseDao;
    public void add(SHIPIN_INFO user, MultipartHttpServletRequest request) {
        if (StringUtil.isBlank(user.getId())) {
        user.setCreate_time(new Date().getTime());
        }
        MultipartFile mFile = request.getFile("content");
        if (mFile != null && !mFile.isEmpty()) {
            UpLoadContext upLoad = new UpLoadContext(
                    new UploadResource());
            String url = upLoad.uploadFile(mFile, null);
            user.setFile_path(url);
        }
        MultipartFile picFile = request.getFile("pic");
        if (picFile != null && !picFile.isEmpty()) {
            UpLoadContext upLoad = new UpLoadContext(
                    new UploadResource());
            String url = upLoad.uploadFile(picFile, null);
            user.setPic_path(url);
        }
        user.insertOrUpdate();
    }

    public Map selectByPk(String id) {
        return
        baseDao.queryForMap("select *,FROM_UNIXTIME(left( create_time,10), '%Y-%m-%d' )  as create_time_str  from SHIPIN_INFO where id ='" + id + "'");
    }

    public void deleteById(String id) {
        SHIPIN_INFO zhuanlan = new SHIPIN_INFO();
        zhuanlan.setId(id);
        zhuanlan.deleteById();
    }

    public Map find(SHIPIN_INFO zhuan, HttpServletRequest request) {
        StringBuffer buffer = new StringBuffer("select * from SHIPIN_INFO where 1=1 ");
        if (StringUtil.isNotBlank(zhuan.getTitle())) {
            buffer.append(" and title like '%").append(zhuan.getTitle()).append("%'");
        }
        buffer.append(" order by create_time desc");
        Map resultMap= baseDao.queryForDataGrid(request,buffer.toString(), new SqlParameter());
        return resultMap;
    }
}
