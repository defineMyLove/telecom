package com.company.modules.web;

import java.io.Serializable;

/**
 * Service 方法执行结果<br>
 * 适用于service需要返回一些消息时
 * @author gengzi
 *
 */
public class ServiceResponse implements Serializable{

	private static final long serialVersionUID = -5130284009910508141L;
	private boolean result;
	private String msg;
	private Object obj;
	
	public ServiceResponse() {
	}
	public ServiceResponse(boolean isSuccess, String msg) {
		super();
		this.result = isSuccess;
		this.msg = msg;
	}
	
	/**
	 * 同时设置isSuccess=false;
	 * @param errorMsg
	 */
	public void setingError(String errorMsg){
		this.result=false;
		this.setMsg(errorMsg);
	}
    /**
     * 同时设置isSuccess=false;
     * @param sucessMsg
     */
    public void setingSucess(String sucessMsg){
        this.result=true;
        this.setMsg(sucessMsg);
    }

    /**
	 * 同时设置isSuccess=false;
	 * @param obj
	 */
	public void setingSucess(Object obj,String sucessMsg){
        this.setingSucess(sucessMsg);
		this.obj=obj;
	}
    /**
     * 同时设置isSuccess=false;
     * @param obj
     */
    public void setingError(Object obj,String errorMsg){
        this.setingError(errorMsg);
        this.obj=obj;
    }

	public boolean getResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
