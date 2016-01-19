package com.company.modules.upload;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.company.electriccar.common.syscontext.SystemContext;
import com.company.modules.utils.JsonUtil;
import com.company.modules.utils.StringUtil;
import com.company.modules.utils.WebUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

/**
 * 此类为 上传文件时所走步骤的封装类
 * 
 * @author zxl :)
 * @version 1.0   
 * date   2011-8-6
 * time   上午11:37:39
 */
public class UpLoadContext {
	//日志
	private static final Logger log = LogManager.getLogger(UpLoadContext.class);
	private UpLoadFile upLoadFile;
	public UpLoadContext(UpLoadFile upLoadFile){
		this.upLoadFile = upLoadFile;
	}
	/**
	 * 上传文件.
	 * @param multipartFile 要上传的文件流对象. 不能为空.
	 * @param response  把上传的结果以json输出到response,如果response为null则不输出.
	 * @return 文件保存成功返回文件保存路径,否则返回null.
	 */
	public String uploadFile(MultipartFile multipartFile,HttpServletResponse response){
		PrintWriter out = null;
		if(response!=null){
			try{
				out = response.getWriter();
			}catch (Exception e) {
				log.error("上传出现错误：" + e.getMessage());
				throw new UploadException("上传出现错误.");
			}
			response.setContentType("text/html;charset=UTF-8");
		}
		MesObject mesObject = new MesObject();//return a MesObject object ,you know.
		upLoadFile.setServletContext(SystemContext.getServletContext());
		upLoadFile.setMultipartFile(multipartFile);
		
		//1.初始化参数
		log.debug("初始化上传文件 "+upLoadFile.getFileName()+" 的参数");
		upLoadFile.initParam();
		//2.验证
		log.debug("验证上传文件");
		mesObject=upLoadFile.validate();
		if(mesObject.getError().equals(1)){
			if(out!=null){
				//out.print(binder.toJson(mesObject));
				return null;
			}else{
				throw new UploadException(mesObject.getMessage());
			}
		}
		//3.创建文件夹
		log.debug("为上传文件创建文件夹");
		mesObject=upLoadFile.createFile();
		if(mesObject.getError().equals(1)){
			if(out!=null){
				//out.print(binder.toJson(mesObject));
				return null;
			}else{
				throw new UploadException(mesObject.getMessage());
			}
		}
		//4.上传
		log.debug("开始上传文件");
		mesObject =upLoadFile.upload();
		
		//5.以Json方式 返回上传结果
		if(out!=null) {
            //out.print(binder.toJson(mesObject));
            StringBuffer buffer = new StringBuffer();
            Map paramMap = new HashMap();
            paramMap.put("error", mesObject.getError());
            paramMap.put("message", mesObject.getMessage());
            paramMap.put("url", mesObject.getUrl());
            WebUtil.write(response,JsonUtil.map2Json(paramMap));
        }
        return mesObject.getUrl();
	}
}
