package com.cosplay.command.entity;

import java.lang.reflect.Method;

public class ReceiverEntity {
	
	//命令
	private String cmd;
	
	//类
	private Object target;
	
	//执行方法
	private Method method;

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	

	
	
	
	
}
