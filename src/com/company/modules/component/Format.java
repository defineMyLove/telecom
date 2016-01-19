package com.company.modules.component;

import com.company.modules.utils.ConvertUtil;
import com.company.modules.utils.DateUtil;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class Format implements TemplateMethodModel {

	//24小时：yyyy-MM-dd HH:mm:ss 
	//12小时：yyyy-MM-dd hh:mm:ss
	
	@Override
	public Object exec(List list) throws TemplateModelException {
		String arg = "";
		if (list.size() == 0) {
			return null;
		}
		String date = ConvertUtil.obj2Str(list.get(0));
		if (StringUtils.isBlank(date)) {
			return null;
		}
		if (list.size() == 1) {
			arg = "yyyy-MM-dd";
		} 
		if (list.size() == 2) {
			arg = ConvertUtil.obj2Str(list.get(0));
		} 
		
		return DateUtil.format("yyyy-MM-dd HH:mm:ss", date);
	}

}
