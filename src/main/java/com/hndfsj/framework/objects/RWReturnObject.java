package com.hndfsj.framework.objects;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RWReturnObject implements Serializable{
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public static final String WARNING = "warning";
	private String status;
	private String message;
	private Object obj;
	
	public RWReturnObject() {
		
	}
	
	public RWReturnObject(String status) {
		this.status = status;
	}
	
	public RWReturnObject(String status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public RWReturnObject(String status, String message, Object obj) {
		this.status = status;
		this.message = message;
		this.obj = obj;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
}
