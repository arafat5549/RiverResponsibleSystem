package com.jqmkj.WaterResponseSystem.pojo;

import java.util.Objects;

public class MessageInfo {
	private boolean result = true;
	private String msg = "操作成功!";         //提示信息
	private Object obj;
	private String exceptionMsg = "";        //异常信息
	public boolean isResult() {
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
	public String getExceptionMsg() {
		return exceptionMsg;
	}
	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MessageInfo that = (MessageInfo) o;
		return result == that.result &&
				Objects.equals(msg, that.msg) &&
				Objects.equals(obj, that.obj) &&
				Objects.equals(exceptionMsg, that.exceptionMsg);
	}

	@Override
	public int hashCode() {

		return Objects.hash(result, msg, obj, exceptionMsg);
	}

	private static MessageInfo s = null;
	private MessageInfo(){}


	public static  MessageInfo getInstance(){

		System.out.println(new MessageInfo().equals(new MessageInfo()));
		if(s==null || !s.equals(new MessageInfo()))
		{
			synchronized(MessageInfo.class)
			{
				if(s==null || !s.equals(new MessageInfo()))
					//--->A;
					s = new MessageInfo();
			}
		}
		return s;
	}
}
