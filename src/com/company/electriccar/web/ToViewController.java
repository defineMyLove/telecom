package com.company.electriccar.web;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 跳转到视图页面，需要参数view
 * @author gengzi
 *
 */
@Controller
public class ToViewController {

	private static final String viewName = "view";
    private static final String paramName="param";

    @RequestMapping("/toView")
	protected String handleRequestInternal(ModelMap map,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String view = request.getParameter(viewName);
        map.addAllAttributes(redirectParam(request));
		return view;
	}

    public static Map<String,String>  redirectParam(HttpServletRequest request){
        Map<String,String>map = new HashMap();
        String param = request.getParameter(paramName);
        if(StringUtils.isNotBlank(param)){
            String[] a = param.split(";");
            for (String b:a){
                String[] c = b.split(":");
                map.put(c[0],c[1]);
            }
        }
        return map;
    }
}
