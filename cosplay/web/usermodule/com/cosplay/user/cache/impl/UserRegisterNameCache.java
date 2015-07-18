package com.cosplay.user.cache.impl;

import org.springframework.stereotype.Repository;

import com.cosplay.cache.export.CacheKeyConstantsExport;
import com.cosplay.cache.export.impl.ReadCacheExportService;
import com.cosplay.user.cache.IUserRegisterNameCache;

@Repository
public class UserRegisterNameCache extends ReadCacheExportService<String,String> implements IUserRegisterNameCache{
	public UserRegisterNameCache(){
		super(CacheKeyConstantsExport.REGISTER_USER_LOGIN_NAME);
	}
}
