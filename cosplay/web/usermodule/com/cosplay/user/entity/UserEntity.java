package com.cosplay.user.entity;


import org.bson.types.ObjectId;

import com.cosplay.serviceserver.base.entity.IEntity;
import com.cosplay.serviceserver.orm.annotation.Collection;
import com.cosplay.serviceserver.orm.annotation.Field;
@Collection(name = "user")
public class UserEntity implements IEntity {

	private static final long serialVersionUID = -482735223095128917L;
	//ID 
	private ObjectId id ;
	//用户类型(邮箱用户、普通用户、手机用户
	private Integer userType ;
	//登录名
	private String userLoginName;
	//昵称（CN
	private String userCosName;
	//密码
	private String userPassWord;
	//性别   [ 0为妹纸，1为汉纸 ,2为未知种类  ]
	private Integer userSex;
	
	@Field(name = "_id")
	public ObjectId getId() {
		return id;
	}
	@Field(name = "user_cos_name")
	public String getUserCosName() {
		return userCosName;
	}
	@Field(name = "user_login_name")
	public String getUserLoginName() {
		return userLoginName;
	}
	@Field(name = "user_pass_word")
	public String getUserPassWord() {
		return userPassWord;
	}
	@Field(name = "user_sex")
	public Integer getUserSex() {
		return userSex;
	}
	@Field(name = "user_type")
	public Integer getUserType() {
		return userType;
	}
	
	
	
	
	
	public void setUserCosName(String userCosName) {
		this.userCosName = userCosName;
	}
	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}
	public void setUserPassWord(String userPassWord) {
		this.userPassWord = userPassWord;
	}
	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}
	
	
	
}
