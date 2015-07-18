package com.cosplay.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cosplay.login.cache.ILoginCache;
import com.cosplay.login.entity.LoginUserEntity;
@Component
class LoginContext {
	
	
	//存储登陆的用户
	@Autowired
	private ILoginCache loginCache;

	/**根据登陆的key来存放用户**/
	void putLoginUserByKey(String key ,LoginUserEntity loginUser){
		loginCache.cacheInsert(key, loginUser);
	}
	/**根据登陆的key来获取用户**/
	LoginUserEntity getLoginUserByKey(String key ){
		return loginCache.cacheLoad(key);
	}
	/**查看是否存在相关key存放的用户*/
	boolean isExist(String key){
		return loginCache.cacheContainsKey(key);
	}
}
