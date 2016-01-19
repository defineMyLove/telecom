package com.company.electriccar.web;

import com.company.modules.component.Format;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.TemplateHashModel;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.ArrayList;
import java.util.List;

public class MyFreeMarkerConfigurer extends FreeMarkerConfigurer {

	/**
	 * 自动加载组件
	 * @param cfg
	 * @version V1.0.0
	 * @author 杨凯
	 * @date Dec 3, 2013 3:25:13 PM
	 */
	private void loadAutoIncludeList(Configuration cfg) {
		
		//日期格式化
		cfg.setSharedVariable("format", new Format());
		
		
		List autoIncludeList = new ArrayList();
		autoIncludeList.add("/component/static_file.html");
		autoIncludeList.add("/component/formui.html");
		autoIncludeList.add("/component/Option.html");
		autoIncludeList.add("/component/tabs.html");
		autoIncludeList.add("/component/select.html");
		cfg.setAutoIncludes(autoIncludeList);
	}
	
	
	private void loadCommonStaticClass(Configuration cfg) {
		try {
			BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
			TemplateHashModel staticModels = wrapper.getStaticModels();
			cfg.setSharedVariable("FreeMarkerUtil", (TemplateHashModel) staticModels.get("cn.ksource.core.util.FreeMarkerUtil"));
			cfg.setSharedVariable("SysConstants", (TemplateHashModel) staticModels.get("cn.ksource.web.SysConstants"));
			cfg.setSharedVariable("Constants", (TemplateHashModel) staticModels.get("cn.ksource.web.Constants"));
			cfg.setSharedVariable("StringUtil", (TemplateHashModel) staticModels.get("cn.ksource.core.util.StringUtil"));
			cfg.setSharedVariable("DateUtil", (TemplateHashModel) staticModels.get("cn.ksource.core.util.DateUtil"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Configuration getConfiguration() {
		Configuration cfg =  super.getConfiguration();
		//禁止本地化寻找
		cfg.setLocalizedLookup(false);
		cfg.setDefaultEncoding("utf-8");
		cfg.setClassicCompatible(true);
		//使用[#]替代<#ftl>
		cfg.setTagSyntax(Configuration.SQUARE_BRACKET_TAG_SYNTAX);
		
		
		loadAutoIncludeList(cfg);
		loadCommonStaticClass(cfg);
		return cfg;
	}
	
}
