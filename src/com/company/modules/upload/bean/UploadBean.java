package com.company.modules.upload.bean;
/**
 * 此类为  上传文件验证类
 * 
 * @author zxl :)
 * @version 1.0   
 * date   2011-8-6
 * time   上午11:28:35
 */
public class UploadBean {
	/** 文件保存目录名 */
	private String folder;
	/** 文件最大尺寸 */
	private Long maxSize;
	/** 文件类型 */
	private String[] fileTypes;
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}
	public Long getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(Long maxSize) {
		this.maxSize = maxSize;
	}
	public String[] getFileTypes() {
		return fileTypes;
	}
	public void setFileTypes(String[] fileTypes) {
		this.fileTypes = fileTypes;
	}

}
