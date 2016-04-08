package com.cosplay.login.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cosplay.base.util.Md5Utils;
import com.cosplay.base.util.StringUtil;
import com.cosplay.bus.client.ClientConstants;
import com.cosplay.bus.code.ErrorCode;
import com.cosplay.bus.event.EventPublicUtil;
import com.cosplay.check.handler.impl.GeetestCheckHandler;
import com.cosplay.check.model.NormalCheckResult;
import com.cosplay.check.service.ICheckService;
import com.cosplay.login.constants.LoginConstants;
import com.cosplay.login.cookie.CookieManager;
import com.cosplay.login.entity.LoginUserEntity;
import com.cosplay.login.event.publish.LoginEvent;
import com.cosplay.login.event.publish.LoginOfflineEvent;
import com.cosplay.login.event.publish.LoginOnlineEvent;
import com.cosplay.login.service.ILoginService;
import com.cosplay.user.export.IUserExportService;
import com.cosplay.user.export.impl.UserWrapper;
import com.cosplay.user.service.IUserService;

@Component
public class LoginServiceImpl implements ILoginService{
	@Autowired
	private LoginContext loginContext;
	@Autowired
	private IUserExportService userExportService;
	@Autowired
	private ICheckService checkService;
	@Autowired
	private IUserService registerService;
	
	@Override
	public JSONObject doLogin(HttpServletRequest request,HttpServletResponse response,LoginUserEntity loginUser) {
		JSONObject result = new JSONObject();
		
		//二维码验证
		NormalCheckResult checkResult = new NormalCheckResult();
		if(!checkService.check(new GeetestCheckHandler(checkResult, request))){
			result.put(ClientConstants.RESULT, false);
			result.put(ClientConstants.ERROR_CODE, ErrorCode.ERROR_1007);
			return result;
		}
		
		
		
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
			result.put(ClientConstants.RESULT, true);
			
			return result;
		}
		//验证是否已登陆----end
		
		
		//未登录的处理-----start
		//为空验证
		if( StringUtil.strIsEmpty(loginUser.getUserLoginName()) ){
			result.put(ClientConstants.RESULT, false);
			result.put(ClientConstants.ERROR_CODE, ErrorCode.ERROR_2000);
			return result;
		}
		if(StringUtil.strIsEmpty(loginUser.getUserPassWord()) ){
			result.put(ClientConstants.RESULT, false);
			result.put(ClientConstants.ERROR_CODE, ErrorCode.ERROR_2001);
			return result;
		}
		//用户名验证
		if(registerService.checkUserNameIsExist(loginUser.getUserLoginName())){
			result.put(ClientConstants.RESULT, false);
			result.put(ClientConstants.ERROR_CODE, ErrorCode.ERROR_2003);
			return result;
		}
		
		//密码验证
		UserWrapper userWrapper = userExportService.checkUserPassWord(loginUser.getUserLoginName(),loginUser.getUserPassWord());
		if ( userWrapper == null ){
			result.put(ClientConstants.RESULT, false);
			result.put(ClientConstants.ERROR_CODE, ErrorCode.ERROR_2002);
			return result;
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
		result.put(ClientConstants.RESULT, true);
		return result;
	}


	@Override
	public JSONObject exit(HttpServletRequest request, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		//删除登录的用户
		LoginUserEntity entity = loginContext.exit((CookieManager.getCookieValueByName(request, LoginConstants.USER_LOGIN_KEY_COOKIE_NAME)));
		if(entity!=null){
			//被删除了 ,发布下线事件
			EventPublicUtil.publish(new LoginOfflineEvent(entity.getUserId()));
			result.put(ClientConstants.RESULT, true);
			
			//删除cookie
			CookieManager.deleteCookie(request, LoginConstants.USER_LOGIN_COSNAME_COOKIE_NAME);
			CookieManager.deleteCookie(request, LoginConstants.USER_LOGIN_KEY_COOKIE_NAME);
			CookieManager.deleteCookie(request, LoginConstants.USER_LOGIN_NAME_COOKIE_NAME);
		}else{
			result.put(ClientConstants.RESULT, true);
		}
		return result;
	}


	@Override
	public ObjectId getOnlineUserId(HttpServletRequest request) {
		ObjectId userId = null;
		if( loginContext.isExist((CookieManager.getCookieValueByName(request, LoginConstants.USER_LOGIN_KEY_COOKIE_NAME))) ){
			LoginUserEntity loginUser = loginContext.getLoginUserByKey(CookieManager.getCookieValueByName(request, LoginConstants.USER_LOGIN_KEY_COOKIE_NAME));
			userId = loginUser.getUserId();
		}
		return userId;
	}
	

}
