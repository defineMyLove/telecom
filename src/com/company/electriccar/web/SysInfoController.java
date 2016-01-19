package com.company.electriccar.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zxl on 14-6-22.
 */
@Controller
@RequestMapping("common")
public class SysInfoController {
    @RequestMapping("sysinfo")
    public String sysInfo(ModelMap map){
        return "common/sysinfo";
    }
}
