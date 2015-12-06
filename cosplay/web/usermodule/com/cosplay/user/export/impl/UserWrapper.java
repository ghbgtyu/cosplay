package com.cosplay.user.export.impl;

import org.bson.types.ObjectId;

public class UserWrapper {
	private String userLoginName;
	private String userLoginCosName;
	private ObjectId userId;
	
	
	public ObjectId getUserId() {
		return userId;
	}
	public String getUserLoginCosName() {
		return userLoginCosName;
	}
	public String getUserLoginName() {
		return userLoginName;
	}
	void setUserId(ObjectId userId) {
		this.userId = userId;
	}
	public void setUserLoginCosName(String userLoginCosName) {
		this.userLoginCosName = userLoginCosName;
	}
	void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}
}
