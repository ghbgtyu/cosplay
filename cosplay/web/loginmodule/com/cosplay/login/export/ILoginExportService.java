package com.cosplay.login.export;

import javax.servlet.http.HttpServletRequest;


public interface ILoginExportService {
	public void deleteUserCookie(HttpServletRequest request);
}
