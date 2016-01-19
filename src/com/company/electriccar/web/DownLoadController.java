package com.company.electriccar.web;

import com.company.electriccar.common.syscontext.SystemContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 文件下载
 * @author gengzi
 *
 */
@Controller
@RequestMapping("/download")
public class DownLoadController {
	private void outFile(MyFile myFile,HttpServletResponse response,HttpServletRequest request) throws IOException{
		InputStream in = null;
		//如果 myFile为空,没有数据源或数据源为空则提示 文件不存在
		if(myFile==null || (myFile.fileisNull()&&myFile.getUrl()==null)||(in =getInputStream(myFile))==null){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write("<script>") ;
			out.write("alert('文件不存在');") ;
			out.write("history.go(-1);") ;
			out.write("</script>") ;
		}else{
			String fileName = myFile.getFileName() ;
			fileName = URLEncoder.encode(fileName, "UTF-8");//在火狐下文件名有问题
	        /*
	         * see http://support.microsoft.com/default.aspx?kbid=816868
	         */
	        if (fileName.length() > 150) {
	            //String guessCharset = request.getLocale().get /*根据request的locale 得出可能的编码，中文操作系统通常是gb2312*/
	            fileName = new String(myFile.getFileName().getBytes("gb2312"), "ISO8859-1"); 
	        }
				response.reset();
				response.setContentType("application/x-msdownload");
				response.setHeader("Content-disposition","attachment; filename="+fileName);
				//以上输出文件元信息
				
				byte[] b = new byte[2048]; 
				int fileLength=0;
				int len = 0; 
				while((len=in.read(b)) >0){
					response.getOutputStream().write(b,0,len);      //向浏览器输出
					fileLength+=len;
				}
				response.setContentLength(fileLength);      //设置输入文件长度
				in.close();         //关闭文件输入流
				response.flushBuffer();
		}
	}
	private InputStream getInputStream(MyFile myFile) {
		InputStream input =null;
		if(myFile.getUrl()==null){
			input= new ByteArrayInputStream(myFile.getFile());
		}else{
			//去掉 绝对路径根目录  如: 路径为/liangfa/resources/new/jquery.js
			//修改后为:/resources/new/jquery.js
			String contextPath = SystemContext.getServletContext().getContextPath();
			String realUrl = myFile.getUrl();
			if(realUrl.startsWith(contextPath)){
				realUrl = realUrl.substring(contextPath.length());
			}
			myFile.setUrl(realUrl);
			File file = new File(SystemContext.getRealPath()+myFile.getUrl());
			try {
				input= new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			
		}
		return input;
		
	}
	
	@RequestMapping("/file")
	public void noticeFile(String url,String fileName,HttpServletResponse response,HttpServletRequest request) throws IOException{
		MyFile myFile = new MyFile();
        myFile.setUrl(url);
        myFile.setFileName(fileName);
        outFile(myFile, response, request);
	}

}
