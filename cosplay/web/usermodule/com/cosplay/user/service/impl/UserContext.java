package com.cosplay.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cosplay.user.cache.IUserRegisterIdCache;
import com.cosplay.user.cache.IUserRegisterNameCache;

@Component
public class UserContext {
	
	@Autowired
	private IUserRegisterNameCache userRegisterName;
	
	@Autowired
	private IUserRegisterIdCache userRegisterId;

	/**根据注册的key来存放用户名字**/
	void putRegisterUserNameByKey(String key ,String loginUser){
		userRegisterName.cacheInsert(key, loginUser);
	}
	/**根据登陆的key来获取用户**/
	String getRegisterUserNameByKey(String key ){
		return userRegisterName.cacheLoad(key);
	}
	/**查看是否存在相关key存放的用户*/
	boolean userNameisExist(String key){
		return userRegisterName.cacheContainsKey(key);
	}
	
	
	//------------------------------------------
	
	
	/**根据注册的key来存放用户ID**/
	void putRegisterUserIdByKey(String key ,String loginUser){
		userRegisterId.cacheInsert(key, loginUser);
	}
	/**根据登陆的key来获取用户**/
	String getRegisterUserIdByKey(String key ){
		return userRegisterId.cacheLoad(key);
	}
	/**查看是否存在相关key存放的用户*/
	boolean userIdisExist(String key){
		return userRegisterId.cacheContainsKey(key);
	}
}
