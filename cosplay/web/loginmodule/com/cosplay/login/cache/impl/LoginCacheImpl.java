package com.cosplay.login.cache.impl;

import org.springframework.stereotype.Component;

import com.cosplay.cache.export.CacheKeyConstantsExport;
import com.cosplay.cache.export.impl.ReadCacheExportService;
import com.cosplay.login.cache.ILoginCache;
import com.cosplay.login.entity.LoginUserEntity;

@Component
public class LoginCacheImpl extends ReadCacheExportService<String,LoginUserEntity> implements ILoginCache{
	public LoginCacheImpl(){
		super(CacheKeyConstantsExport.LOGIN_USER);
	}
}
