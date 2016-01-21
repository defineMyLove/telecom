package com.company.electriccar.service.reader;

import com.company.electriccar.domain.PRO_FLAG;
import com.company.electriccar.domain.PRO_FLAG_RECORD;
import com.company.electriccar.domain.PRO_READER;
import com.company.modules.dao.BaseDao;
import com.company.modules.dao.SqlParameter;
import com.company.modules.utils.DateUtil;
import com.company.modules.utils.StringUtil;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zxl on 14-6-22.
 */
@Service
public class FlagService {
    @Autowired
    private BaseDao baseDao;

    public void add(PRO_FLAG user) {
        if (StringUtil.isBlank(user.getId())) {
            // user.setCreate_time(new Date().getTime());
        }
        user.insertOrUpdate();
    }

    public Map selectByPk(String id) {
        return
                baseDao.queryForMap("select *,FROM_UNIXTIME(left( create_time,10), '%Y-%m-%d' )  as create_time_str  from PRO_FLAG where id ='" + id + "'");
    }

    public void deleteById(String id) {
        PRO_FLAG zhuanlan = new PRO_FLAG();
        zhuanlan.setId(id);
        zhuanlan.deleteById();
    }

    public Map find(PRO_FLAG zhuan, HttpServletRequest request) {
        StringBuffer buffer = new StringBuffer("select *,FROM_UNIXTIME(left( create_time,10), '%Y-%m-%d' ) as createTime from PRO_FLAG where 1=1 ");
        if (StringUtil.isNotBlank(zhuan.getFlag_id())) {
            buffer.append(" and flag_id like %" + zhuan.getFlag_id() + "%");
        }
        if (StringUtil.isNotBlank(zhuan.getRaft_id())) {
            buffer.append(" and raft_id like %" + zhuan.getRaft_id() + "%");
        }
        buffer.append(" order by create_time desc");
        Map resultMap = baseDao.queryForDataGrid(request, buffer.toString(), new SqlParameter());
        return resultMap;
    }

    /**
     * 查询
     *
     * @param zhuan
     * @param startTime
     * @param endTime
     * @param request
     * @return
     */
    public Map query(PRO_FLAG zhuan, String startTime, String endTime, HttpServletRequest request) {
        StringBuffer buffer = new StringBuffer("select *,FROM_UNIXTIME(left( startTime,10), '%Y-%m-%d %H:%i' ) as startTimeStr" +
                ",FROM_UNIXTIME(left( endTime,10), '%Y-%m-%d %H:%i' ) as endTimeStr from PRO_FLAG_RECORD where 1=1 ");
        if (StringUtil.isNotBlank(zhuan.getFlag_id())) {
            buffer.append(" and flagId like '%" + zhuan.getFlag_id() + "%'");
        }
        if (StringUtil.isNotBlank(zhuan.getRaft_id())) {
            buffer.append(" and RaltId like '%" + zhuan.getRaft_id() + "%'");
        }
        if (StringUtil.isNotBlank(startTime)) {
            buffer.append(" and startTime >" + DateUtil.convertStringToDate(startTime).getTime());
        }
        if (StringUtil.isNotBlank(endTime)) {
            buffer.append(" and startTime <" + DateUtil.convertStringToDate(startTime).getTime());
        }
        buffer.append(" order by startTime desc");
        Map resultMap = baseDao.queryForDataGrid(request, buffer.toString(), new SqlParameter());
        return resultMap;
    }

    public Map analysis(String raftId, String month, HttpServletRequest request) {
        StringBuffer buffer = new StringBuffer("select raltId,FROM_UNIXTIME(left(startTime,10), '%Y-%m' ) as months,sum(personCount) as personNum FROM " +
                " pro_flag_record where 1=1 ");
        if (StringUtil.isNotBlank(month)) {
            buffer.append(" and FROM_UNIXTIME(left(startTime,10), '%Y-%m' ) =" + month);
        } else {
            buffer.append(" and FROM_UNIXTIME(left(startTime,10), '%Y-%m' ) ='" + DateUtil.convertMillisToString(new Date().getTime(), "yyyy-MM") + "'");
        }
        if (StringUtil.isNotBlank(raftId)) {
            buffer.append(" and RaltId like '%" + raftId + "%'");
        }
        buffer.append("group by raltId,months order by raltId asc");
        Map resultMap = baseDao.queryForDataGrid(request, buffer.toString(), new SqlParameter());
        return resultMap;
    }

    public Map search(HttpServletRequest request) {
        StringBuffer buffer = new StringBuffer("SELECT id as flagKey, raft_id,flag_id,state,(SELECT personCount FROM pro_flag_record WHERE flag_id = flagId AND raft_id = RaltId AND endTime IS NULL ) AS personCount");
        buffer.append(" FROM pro_flag");
        buffer.append(" order by raft_id asc");
        Map resultMap = baseDao.queryForDataGrid(request, buffer.toString(), new SqlParameter());
        return resultMap;
    }

    /**
     * 发排(1.判断当前木筏的状态2.设置状态3.添加记录)
     *
     * @param id
     * @param personNum
     * @param request   @return
     */
    public Map setFlag(String id, String personNum, HttpServletRequest request) {
        Map resultMap = new HashMap();
        Map flagMap = baseDao.queryForMap("select raft_id,flag_id,state from pro_flag where id ='" + id+"'");
        Integer state = (Integer) flagMap.get("state");
        if (state != null) {
            if (state == 1) { //在用
                resultMap.put("code", 1);
                resultMap.put("msg", "此木筏已经发排");
            } else {//空闲
                resultMap.put("code", 0);
                PRO_FLAG flag = new PRO_FLAG();
                flag.setId(id);
                flag.setState(1);
                flag.update();
                PRO_FLAG_RECORD record = new PRO_FLAG_RECORD();
                record.setStarttime(new Date().getTime());
                record.setPersoncount(Integer.parseInt(personNum));
                record.setFlagid(String.valueOf(flagMap.get("flag_id")));
                record.setRaltid(String.valueOf(flagMap.get("raft_id")));
                record.insert();
            }
        }
        return resultMap;
    }

    public Map addFlag(String flagId, String raftId) {
        Map resultMap = new HashMap();
        //查询是否已经有对应的数据
        int count = baseDao.queryForInteger("select count(*) from pro_flag where flag_id ='" + flagId + "'");
        if (count > 0) {
            resultMap.put("code", 1);
            resultMap.put("msg","编号"+flagId+"木筏已经存在!");
        }else{
            PRO_FLAG flag = new PRO_FLAG();
            flag.setState(0);
            flag.setFlag_id(flagId);
            flag.setRaft_id(raftId);
            flag.setCreate_time(new Date().getTime());
            flag.insert();
            resultMap.put("code", 0);
        }
        return resultMap;
    }

    public Map updateFlag(PRO_FLAG flag) {
        Map resultMap = new HashMap();
        if (StringUtil.isNotBlank(flag.getId()) && StringUtil.isNotBlank(flag.getFlag_id())) {
            flag.update();
            resultMap.put("code", 0);
        } else {
            resultMap.put("code", 1);
            resultMap.put("msg","参数异常");
        }
        return resultMap;
    }

    public Map loginCheck(String name, String pwd, HttpServletRequest request) {
        Map resultMap = new HashMap();
        Map paramMap = new HashMap();
        paramMap.put("name", name);
        paramMap.put("pwd", pwd);
       int count= baseDao.queryForInteger("select count(*) from pro_user where name =:name and pwd=:pwd", paramMap);
        if (count > 0) {
            resultMap.put("code", 0);
        }else {
            resultMap.put("code", 1);
        }
        return resultMap;
    }


}
