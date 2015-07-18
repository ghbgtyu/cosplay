package com.cosplay.user.export.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cosplay.user.entity.UserEntity;
import com.cosplay.user.export.IUserExportService;
import com.cosplay.user.service.IUserService;
@Component
public class UserExportService implements IUserExportService {
	@Autowired
	private IUserService userService;

	@Override
	public UserWrapper checkUserPassWord(String userLoginName,String userPassWord) {
		UserEntity user  = userService.findUser(userLoginName, userPassWord);
		if( user == null ){
			return null;
		}
		UserWrapper userWrapper = new UserWrapper();
		userWrapper.setUserId(user.getId());
		userWrapper.setUserLoginName(user.getUserLoginName());
		return userWrapper;
	}

}
