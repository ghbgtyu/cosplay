package com.cosplay.user.export;

import com.cosplay.user.export.impl.UserWrapper;

public interface IUserExportService {
	/**验证用户密码*/
	public UserWrapper checkUserPassWord(String userLoginName,String userPassWord);
}
