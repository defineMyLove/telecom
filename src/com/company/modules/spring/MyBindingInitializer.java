package com.company.modules.spring;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: zfzone
 * Date: 2010-7-29
 * Time: 18:13:28
 * To change this template use File | Settings | File Templates.
 */
public class MyBindingInitializer implements WebBindingInitializer {
    public void initBinder(WebDataBinder webDataBinder, WebRequest webRequest) {
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
//        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }
}
