package com.cosplay.login.export;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;


public interface ILoginExportService {
	public void deleteUserCookie(HttpServletRequest request);
	
	/**获取在线userId
	 * @return if null ,not login
	 * */
	public ObjectId getOnlineUserId(HttpServletRequest request);
}
