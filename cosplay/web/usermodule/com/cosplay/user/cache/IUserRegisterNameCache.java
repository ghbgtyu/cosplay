package com.cosplay.user.cache;

import com.cosplay.cache.export.ICacheExportService;

/**注册时检验用户昵称，已经存在的放到缓存*/
public interface IUserRegisterNameCache extends ICacheExportService<String,String> {

}
