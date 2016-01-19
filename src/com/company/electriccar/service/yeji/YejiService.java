package com.company.electriccar.service.yeji;

import com.company.electriccar.domain.YEJI_INFO;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zxl on 14-6-22.
 */
@Service
public class YejiService {
    @Autowired
    private BaseDao baseDao;
    public void add(YEJI_INFO user, MultipartHttpServletRequest request) {
        if (StringUtil.isBlank(user.getId())) {
        user.setCreate_time(new Date().getTime());
        }
        user.insertOrUpdate();
       List<MultipartFile>  mFiles = request.getFiles("file");
        List<SqlParameter> sqlParameters = new ArrayList<SqlParameter>();
        boolean isFirst =true;
        for(MultipartFile mFile:mFiles) {
            SqlParameter sqlParameter = new SqlParameter();
            if (mFile != null && !mFile.isEmpty()) {
                UpLoadContext upLoad = new UpLoadContext(
                        new UploadResource());
                String url = upLoad.uploadFile(mFile, null);
                String fileName = mFile.getOriginalFilename();
                sqlParameter.addValue("name", fileName).addValue("url",url)
                .addValue("yeji_id",user.getId()).addValue("id",StringUtil.getUUID());
                if(isFirst){//第一张图片默认为首页展示图片
                    sqlParameter.addValue("type", 0);
                    isFirst=false;
                }else {
                    sqlParameter.addValue("type", 1);
                }
                sqlParameters.add(sqlParameter);
            }
        }
        if (!sqlParameters.isEmpty()) {
        baseDao.executeBatch("insert into yeji_file(id,path,name,yeji_id,type) values(:id,:url,:name,:yeji_id,:type)",sqlParameters);
        }
    }

    public Map selectByPk(String id) {
        return
        baseDao.queryForMap("select *,FROM_UNIXTIME(left( create_time,10), '%Y-%m-%d' )  as create_time_str  from YEJI_INFO where id ='" + id + "'");
    }

    public void deleteById(String id) {
        YEJI_INFO zhuanlan = new YEJI_INFO();
        zhuanlan.setId(id);
        zhuanlan.deleteById();
    }

    public Map find(YEJI_INFO zhuan, HttpServletRequest request) {
        StringBuffer buffer = new StringBuffer("select * from YEJI_INFO where 1=1 ");
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

    public List<Map> findYejiPic(int i) {
        String sql = "select y.id,y.name,f.path as pic_path from YEJI_INFO y,yeji_file f where y.id=f.yeji_id and  f.type=0 limit 0,"+i;
        return baseDao.queryForList(sql);
    }

    public List<Map> selectFiles(String id) {
        return baseDao.queryForList("select * from yeji_file where yeji_id ='"+id+"'");
    }
}
