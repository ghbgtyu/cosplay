package com.cosplay.check.handler.impl;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;

import com.cosplay.bus.code.ErrorCode;
import com.cosplay.check.handler.AbsCheckHandler;
import com.cosplay.check.model.LoginCheckResult;
import com.cosplay.login.service.ILoginService;

/**检验是否登陆*/
public class LoginCheckHandler extends AbsCheckHandler {
	
	private ILoginService loginService;
	
	private HttpServletRequest request;
	
	private LoginCheckResult loginCheckResult;
	
	public LoginCheckHandler(HttpServletRequest request,ILoginService loginService,LoginCheckResult loginCheckResult){
		this.request = request;
		this.loginService = loginService;
		this.loginCheckResult = loginCheckResult;
	}

	@Override
	public boolean check() {
		
		ObjectId id = loginService.getOnlineUserId(request);
		if(id == null){
			//检验失败
			this.loginCheckResult.setSuccess(false);
			this.loginCheckResult.setErrorCode(ErrorCode.ERROR_1001);
			return false;
		}else{
			this.loginCheckResult.setSuccess(true);
			this.loginCheckResult.setId(id);
			return true;
		}
	}

}
