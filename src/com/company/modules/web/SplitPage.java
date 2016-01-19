package com.company.modules.web;

import com.company.modules.utils.StringUtil;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


public class SplitPage {

	private int infoCount; 			//��Ϣ����
	private int pageSize; 			//ÿҳ��Ϣ����
	private int currentPageIndex;	//��ǰҳ��
	private int pageCount; 			//��ҳ��
	private int page;
	
	private boolean hasNext; 		//�Ƿ��к�ҳ
	private boolean hasPrevious; 	//�Ƿ���ǰҳ 
	private HttpServletRequest request;
	
	public SplitPage(){
		
	}
	
	public SplitPage(int pageSize, HttpServletRequest request){
		this.pageSize = pageSize;
		this.request = request;
	}
	
	public SplitPage(int pageSize, int currentPageIndex){
		this.pageSize = pageSize;
		this.currentPageIndex = currentPageIndex;
	}
	
	public SplitPage setInfo(int pageSize,int currentPageIndex){
		this.pageSize = pageSize;
		this.currentPageIndex = currentPageIndex;
		return this;
	}
	
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		String currentPageIndex = request.getParameter("currentPageIndex");
		if (StringUtils.isBlank(currentPageIndex)) {
			currentPageIndex = "1";
		}
		setCurrentPageIndex(Integer.parseInt(currentPageIndex));
		this.request = request;
	}

	public int getInfoCount() {
		return infoCount;
	}
	public void setInfoCount(int infoCount) {
		this.infoCount = infoCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPageIndex() {
		String pageIndex = Integer.valueOf(this.currentPageIndex) == null || this.currentPageIndex == 0 ? 
				(StringUtils.isBlank(request.getParameter("pageindex")) ? "0" : request.getParameter("pageindex")) 
				: 
				String.valueOf(currentPageIndex);
				
//		String pageIndex = Integer.valueOf(this.currentPageIndex) == null ? request.getParameter("pageindex") : String.valueOf(currentPageIndex);
		if (StringUtils.isBlank(pageIndex) ||  StringUtils.equals("0", pageIndex)) {
			this.currentPageIndex = 1;
			return this.currentPageIndex;
		} 
		if (StringUtil.isInteger(pageIndex)) {
			this.currentPageIndex = Integer.valueOf(pageIndex);
			if (this.currentPageIndex < 0) {
				this.currentPageIndex = 1;
			} else if (this.currentPageIndex > getPageCount()) {
				this.currentPageIndex = pageCount;
			}
		} else {
			this.currentPageIndex = 1;
		}
		return currentPageIndex;
	}
	public void setCurrentPageIndex(int currentPageIndex) {
		this.currentPageIndex = currentPageIndex;
	}
	public int getPageCount() {
		if (infoCount == 0) {
			this.pageCount =  1;
			return pageCount;
		}
		if (infoCount/pageSize == 0) {
			this.pageCount = 1;
			return pageCount;
		}
		if (infoCount%pageSize == 0) {
			this.pageCount = infoCount/pageSize;
			return pageCount;
		}
		this.pageCount =  infoCount/pageSize+1;
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public boolean isHasNext() {
		if (getCurrentPageIndex() < getPageCount()) {
			this.hasNext = true;
		} else {
			this.hasNext = false;
		}
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	public boolean isHasPrevious() {
		if (getCurrentPageIndex() > 1) {
			this.hasPrevious = true;
		} else {
			this.hasPrevious =  false;
		}
		return hasPrevious;
	}
	
	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}
	
	public List<Integer> getPageList(){
		List<Integer> list = new ArrayList<Integer>();
		int j = 0;
		int count = getPageCount();
		int current = getCurrentPageIndex();
//		if (current < 10) {
//			j = 0;
//			count = getPageCount() > 10 ? 10 : getPageCount();
//		}
//		if (current > 10 && current < 15) {
//			j = 5;
//			count = getPageCount() > 15 ? 15 : getPageCount();
//		}
//		if (current > 15 && current < 20) {
//			j = 10;
//			count = getPageCount() > 20 ?  20 : getPageCount();
//		}
		for (int i = j ; i<count;i++) {
			list.add(i+1);
		}
		return list;
	}

	/**
	 * ��������ȡ�õ�ǰҳ���URL<BR>
	 * @return
	 * @author:�<BR>
	 * ʱ�䣺Apr 18, 2009 9:15:27 PM<BR>
	 */
	public String getUrl() {
		String queryParam = request.getQueryString();
		if (StringUtils.isBlank(queryParam)) {
			return request.getRequestURI()+"?";
		} else {
			String[] params = queryParam.split("&");
			String strQuery = "";
			for (String string : params) {
				if (!StringUtils.contains(string, "currentPageIndex")) {
					strQuery += string + "&";
				}
			}
			return request.getRequestURI() + "?" + strQuery;
		}
	}
	
	public void setUrl(String url){
		
	}
	


	public int getPage() {
		return page;
	}



	public void setPage(int page) {
		this.page = page;
	}
	
}
