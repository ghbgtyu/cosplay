package com.cosplay.user.cache.impl;

import org.springframework.stereotype.Repository;

import com.cosplay.cache.export.CacheKeyConstantsExport;
import com.cosplay.cache.export.impl.ReadCacheExportService;
import com.cosplay.user.cache.IUserRegisterIdCache;

@Repository
public class UserRegisterIdCache extends ReadCacheExportService<String,String> implements IUserRegisterIdCache{
	public UserRegisterIdCache(){
		super(CacheKeyConstantsExport.REGISTER_USER_LOGIN_ID);
	}
}
