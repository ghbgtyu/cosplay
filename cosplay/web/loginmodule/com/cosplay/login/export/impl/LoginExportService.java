package com.cosplay.login.export.impl;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cosplay.login.constants.LoginConstants;
import com.cosplay.login.cookie.CookieManager;
import com.cosplay.login.export.ILoginExportService;
import com.cosplay.login.service.ILoginService;

@Service
public class LoginExportService implements ILoginExportService {
	
	@Autowired
	private ILoginService lognService;

	@Override
	public void deleteUserCookie(HttpServletRequest request) {
		CookieManager.deleteCookie(request,LoginConstants.USER_LOGIN_KEY_COOKIE_NAME);
	}

	@Override
	public ObjectId getOnlineUserId(HttpServletRequest request) {
		return lognService.getOnlineUserId(request);
	}

}
