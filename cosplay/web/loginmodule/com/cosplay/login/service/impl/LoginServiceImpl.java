package com.cosplay.login.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cosplay.base.util.Md5Utils;
import com.cosplay.base.util.StringUtil;
import com.cosplay.bus.event.EventPublicUtil;
import com.cosplay.login.constants.LoginConstants;
import com.cosplay.login.cookie.CookieManager;
import com.cosplay.login.entity.LoginUserEntity;
import com.cosplay.login.event.publish.LoginEvent;
import com.cosplay.login.event.publish.LoginOnlineEvent;
import com.cosplay.login.service.ILoginService;
import com.cosplay.user.export.IUserExportService;
import com.cosplay.user.export.impl.UserWrapper;

@Component
public class LoginServiceImpl implements ILoginService{
	@Autowired
	private LoginContext loginContext;
	@Autowired
	private IUserExportService userExportService;
	
	
	@Override
	public LoginUserEntity doLogin(HttpServletRequest request,HttpServletResponse response,LoginUserEntity loginUser) {
		loginUser.setLoginState(false);
		//验证是否已登陆----start
	/*	if( !StringUtil.strIsEmpty(loginUser.getUserLoginKey())){
			
		}*/
		if( loginContext.isExist((CookieManager.getCookieValueByName(request, LoginConstants.USER_LOGIN_KEY_COOKIE_NAME))) ){
			loginUser = loginContext.getLoginUserByKey(CookieManager.getCookieValueByName(request, LoginConstants.USER_LOGIN_KEY_COOKIE_NAME));
			//重新设key
			loginUser.setUserLoginKey(Md5Utils.md5To16(loginUser.getUserLoginName()+System.currentTimeMillis()));
			loginUser.setLoginState(true);
			loginContext.putLoginUserByKey(loginUser.getUserLoginKey(), loginUser);
			EventPublicUtil.publish(new LoginOnlineEvent(loginContext.getLoginUserByKey(CookieManager.getCookieValueByName(request, LoginConstants.USER_LOGIN_KEY_COOKIE_NAME)).getUserId()));
			return loginUser;
		}
		//验证是否已登陆----end
		
		
		//未登录的处理-----start
		//为空验证
		if( StringUtil.strIsEmpty(loginUser.getUserPassWord()) || StringUtil.strIsEmpty(loginUser.getUserLoginName()) ){
			return loginUser;
		}
		//密码验证
		UserWrapper userWrapper = userExportService.checkUserPassWord(loginUser.getUserLoginName(),loginUser.getUserPassWord());
		if ( userWrapper == null ){
			return loginUser;
		}
		//验证通过
		loginUser.setLoginState(true);
		//设置key,md5
		loginUser.setUserLoginKey(Md5Utils.md5To16(loginUser.getUserLoginName()+System.currentTimeMillis()));
		//设置id
		loginUser.setUserId(userWrapper.getUserId());
		//设置昵称
		loginUser.setUserLoginCosName(userWrapper.getUserLoginCosName());
		
		loginContext.putLoginUserByKey(loginUser.getUserLoginKey(), loginUser);
		
		//设置cookie
		CookieManager.addCookie(response, LoginConstants.USER_LOGIN_COSNAME_COOKIE_NAME, loginUser.getUserLoginCosName());
		CookieManager.addCookie(response, LoginConstants.USER_LOGIN_KEY_COOKIE_NAME, loginUser.getUserLoginKey());
		CookieManager.addCookie(response, LoginConstants.USER_LOGIN_NAME_COOKIE_NAME, loginUser.getUserLoginName());
		//发布事件
		EventPublicUtil.publish(new LoginEvent(loginUser.getUserId()));
		EventPublicUtil.publish(new LoginOnlineEvent(loginUser.getUserId()));
		//未登录的处理-----end
		return loginUser;
	}

}
