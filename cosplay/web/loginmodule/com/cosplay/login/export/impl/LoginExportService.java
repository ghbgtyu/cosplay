package com.cosplay.login.export.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.cosplay.login.constants.LoginConstants;
import com.cosplay.login.cookie.CookieManager;
import com.cosplay.login.export.ILoginExportService;

@Service
public class LoginExportService implements ILoginExportService {

	@Override
	public void deleteUserCookie(HttpServletRequest request) {
		CookieManager.deleteCookie(request,LoginConstants.USER_LOGIN_KEY_COOKIE_NAME);
	}

}
