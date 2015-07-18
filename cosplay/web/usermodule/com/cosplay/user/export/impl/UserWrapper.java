package com.cosplay.user.export.impl;

public class UserWrapper {
	private String userLoginName;
	private String userLoginCosName;
	private Object userId;
	
	
	public Object getUserId() {
		return userId;
	}
	public String getUserLoginCosName() {
		return userLoginCosName;
	}
	public String getUserLoginName() {
		return userLoginName;
	}
	void setUserId(Object userId) {
		this.userId = userId;
	}
	public void setUserLoginCosName(String userLoginCosName) {
		this.userLoginCosName = userLoginCosName;
	}
	void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}
}
