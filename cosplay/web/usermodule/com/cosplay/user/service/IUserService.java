package com.cosplay.user.service;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.cosplay.user.entity.UserEntity;


public interface IUserService {
	/***注册用户*/
	public JSONObject registerUser(UserEntity user, HttpServletRequest request);
	/**验证用户名是否存在*/
	boolean checkUserNameIsExist(String userLoginName);
	/**验证昵称是否存在*/
	boolean checkUserCosNameIsExist(String userCosName);
	/**验证密码是否正确,正确返回true ，错误返回false*/
	boolean checkUserPassword(String userLoginName,String userPassWord);
	/**查询用户，根据账号密码*/
	UserEntity findUser(String userLoginName,String userPassWord);
}
