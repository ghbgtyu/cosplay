package com.cosplay.login.cache;

import com.cosplay.cache.export.ICacheExportService;
import com.cosplay.login.entity.LoginUserEntity;


public interface ILoginCache extends ICacheExportService<String,LoginUserEntity> {
	
}
