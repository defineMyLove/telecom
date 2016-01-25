package com.company.electriccar.service.solution;

import com.company.electriccar.domain.FANGAN_INFO;
import com.company.modules.dao.BaseDao;
import com.company.modules.dao.SqlParameter;
import com.company.modules.upload.UpLoadContext;
import com.company.modules.upload.UploadResource;
import com.company.modules.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zxl on 14-6-22.
 */
@Service
public class SolutionService {
    @Autowired
    private BaseDao baseDao;
    public void add(FANGAN_INFO user, MultipartHttpServletRequest request) {
        if (StringUtil.isBlank(user.getId())) {

        user.setCreate_time(new Date().getTime());
        }
        MultipartFile mFile = request.getFile("file");
        if (mFile != null && !mFile.isEmpty()) {
            UpLoadContext upLoad = new UpLoadContext(
                    new UploadResource());
            String url = upLoad.uploadFile(mFile, null);
            String fileName = mFile.getOriginalFilename();
            user.setPic_path(url);
        }
        int cardNo = baseDao.queryForInteger("SELECT nextval('sequence');");
        user.setOrder_no(cardNo);
        user.insertOrUpdate();
    }

    public Map selectByPk(String id) {
        return
        baseDao.queryForMap("select *,FROM_UNIXTIME(left( create_time,10), '%Y-%m-%d' )  as create_time_str  from fangan_info where id ='" + id + "'");
    }

    public void deleteById(String id) {
        FANGAN_INFO zhuanlan = new FANGAN_INFO();
        zhuanlan.setId(id);
        zhuanlan.deleteById();
    }

    public Map find(FANGAN_INFO zhuan, HttpServletRequest request) {
        StringBuffer buffer = new StringBuffer("select * from fangan_info where 1=1 ");
        if (StringUtil.isNotBlank(zhuan.getName())) {
            buffer.append(" and name like '%").append(zhuan.getName()).append("%'");
        }
        if (StringUtil.isNotBlank(zhuan.getFenlei_id())) {
            buffer.append(" and fenlei_id = '").append(zhuan.getFenlei_id()).append("'");
        }
        buffer.append(" order by create_time desc");
        Map resultMap= baseDao.queryForDataGrid(request,buffer.toString(), new SqlParameter());
        return resultMap;
    }

    public List<Map> find(FANGAN_INFO fangan, int i) {
        StringBuffer buffer = new StringBuffer("select * from fangan_info where 1=1 ");
        if (StringUtil.isNotBlank(fangan.getName())) {
            buffer.append(" and name like '%").append(fangan.getName()).append("%'");
        }
        buffer.append(" order by create_time desc");
        Map resultMap= baseDao.queryForDataGrid(1,10, buffer.toString(), new SqlParameter());
        return (List<Map>) resultMap.get("rows");
    }

    public List<Map> findAll() {
        StringBuffer buffer = new StringBuffer("select * from fangan_info where 1=1 order by create_time desc");
        return baseDao.queryForList(buffer.toString());
    }
}
