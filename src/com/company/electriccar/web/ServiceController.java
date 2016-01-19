package com.company.electriccar.web;

import com.company.electriccar.domain.PRO_FLAG;
import com.company.electriccar.service.reader.FlagService;
import com.company.modules.utils.JsonUtil;
import com.company.modules.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * User: zxl
 * Date: 13-10-22
 * Time: 下午4:20
 * 手机服务接口
 */
@Controller
@RequestMapping("service/flag")
public class ServiceController {
    @Autowired
    FlagService zhuanLanService;

    /**
     * 木筏查询
     * {
     * total:100,
     * page:2,
     * rows:[{
     *
     *
     *   }]
     * }
     * @param request
     * @param response
     */
    @RequestMapping(value = "search")
    public void login(HttpServletRequest request, HttpServletResponse response) {
        Map map = zhuanLanService.search(request);
        map.put("code", 0);
        WebUtil.write(response, JsonUtil.map2Json(map));
    }
    @RequestMapping(value = "loginCheck")
    public void loginCheck(String name,String pwd,HttpServletRequest request, HttpServletResponse response) {
        Map map = zhuanLanService.loginCheck(name, pwd, request);
        WebUtil.write(response, JsonUtil.map2Json(map));
    }

    /**
     * 发排接口
     * @param id 木筏ID
     * @param personNum  乘坐人数
     * @param request
     * @param response
     */
    @RequestMapping(value = "setFlag")
    public void setFlag(String id,String personNum,HttpServletRequest request, HttpServletResponse response) {

        WebUtil.write(response, JsonUtil.map2Json(zhuanLanService.setFlag(id, personNum, request)));
    }

    /**
     * 添加木筏
     * @param flagId 木筏编号
     * @param raftId 标签编号
     * @param request
     * @param response
     */
    @RequestMapping(value = "addFlag")
    public void addFlag(String flagId,String raftId,HttpServletRequest request, HttpServletResponse response) {
        WebUtil.write(response, JsonUtil.map2Json(zhuanLanService.addFlag(flagId, raftId)));
    }

    /**
     * 修改木筏
     * @param flag_id  木筏编号
     * @param raft_id  标签编号
     * @param request
     * @param response
     */
    @RequestMapping(value = "updateFlag")
    public void updateFlag(PRO_FLAG flag,HttpServletRequest request, HttpServletResponse response) {
        WebUtil.write(response, JsonUtil.map2Json(zhuanLanService.updateFlag(flag)));
    }

    /**
     * 木筏编号查询、木筏日期：木筏编号、发排时间、结束时间、时长、状态
     * @param raftId 木筏编号
     * @param month  月份字符串
     * @param request
     * @param response
     */
    @RequestMapping(value = "query")
    public void query(String raftId,String month,HttpServletRequest request, HttpServletResponse response) {
        WebUtil.write(response, JsonUtil.map2Json(zhuanLanService.analysis(raftId, month, request)));
    }

}