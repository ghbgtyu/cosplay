package com.cosplay.login.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cosplay.login.constants.LoginConstants;
import com.cosplay.login.entity.LoginUserEntity;
import com.cosplay.login.service.ILoginService;

/**登陆模块*/
@Controller
public class LoginAction {
	@Autowired
	private ILoginService loginService;
	
	/**Ajax 登陆用户*/
	@RequestMapping(value="/checkUserLoginName",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> userLogin(HttpServletRequest request,HttpServletResponse response,LoginUserEntity loginUser){
		//登陆操作
		loginService.doLogin(request,response,loginUser);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put(LoginConstants.AJAX_CHECK_LOGIN_RESULT, loginUser.getLoginState());
		resultMap.put(LoginConstants.USER_LOGIN_COSNAME_COOKIE_NAME, loginUser.getUserLoginCosName());
		return resultMap;
	}
}
