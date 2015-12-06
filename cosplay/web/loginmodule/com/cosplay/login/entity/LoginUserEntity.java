package com.cosplay.login.entity;

import org.bson.types.ObjectId;

import com.cosplay.serviceserver.base.entity.IEntity;

public class LoginUserEntity implements IEntity {
	private static final long serialVersionUID = -3249854292700174716L;

	private String userLoginName;
	
	private String userPassWord;
	
	private String userLoginKey;
	
	private Boolean loginState;
	
	private ObjectId userId;
	
	private String userLoginCosName;

	public Boolean getLoginState() {
		return loginState;
	}

	public ObjectId getUserId() {
		return userId;
	}

	public String getUserLoginCosName() {
		return userLoginCosName;
	}

	public String getUserLoginKey() {
		return userLoginKey;
	}

	public String getUserLoginName() {
		return userLoginName;
	}

	public String getUserPassWord() {
		return userPassWord;
	}

	public void setLoginState(Boolean loginState) {
		this.loginState = loginState;
	}

	public void setUserId(ObjectId userId) {
		this.userId = userId;
	}

	public void setUserLoginCosName(String userLoginCosName) {
		this.userLoginCosName = userLoginCosName;
	}

	public void setUserLoginKey(String userLoginKey) {
		this.userLoginKey = userLoginKey;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

	public void setUserPassWord(String userPassWord) {
		this.userPassWord = userPassWord;
	}
}
