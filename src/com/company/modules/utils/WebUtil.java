package com.company.modules.utils;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * Created by zxl on 14-6-22.
 */
public class WebUtil {
    public static ModelAndView goSysInfoPage(HttpServletRequest request,String message,String autoScript){
        ModelAndView view = new ModelAndView("redirect:/common/sysinfo");
        HttpSession session = request.getSession();
        session.setAttribute("message", message);
        session.setAttribute("autoScript", autoScript);
        return view;
    }

    public static void write(HttpServletResponse response, String str){
        try {
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            PrintWriter writer =response.getWriter();
            writer.print(str);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeJson(HttpServletResponse response, String str){
        try {
            response.setContentType("application/json; charset=utf-8");
            response.setCharacterEncoding("utf-8");
            PrintWriter writer =response.getWriter();
            writer.print(str);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
