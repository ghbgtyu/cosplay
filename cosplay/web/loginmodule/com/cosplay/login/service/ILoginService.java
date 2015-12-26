package com.cosplay.login.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;

import com.cosplay.login.entity.LoginUserEntity;

public interface ILoginService {
	/***登陆操作
	 * @param request 
	 * @param response */
	public LoginUserEntity doLogin(HttpServletRequest request, HttpServletResponse response, LoginUserEntity loginUser);
	
	/**注销登录*/
	public boolean exit(HttpServletRequest request, HttpServletResponse response);
	
	/**获取在线userId*/
	public ObjectId getOnlineUserId(HttpServletRequest request);
}
