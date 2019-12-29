package com.qf.model;

public class BaseBean {
	private int code;
	private String msg;
	private Object obj;
	public BaseBean(int code, String msg, Object obj) {
		super();
		this.code = code;
		this.msg = msg;
		this.obj = obj;
	}
	public BaseBean() {
		super();
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
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
	@Override
	public String toString() {
		return "BaseBean [code=" + code + ", msg=" + msg + ", obj=" + obj + "]";
	}
	
}
