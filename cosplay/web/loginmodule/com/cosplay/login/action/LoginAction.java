package com.cosplay.login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.alibaba.fastjson.JSONObject;
import com.cosplay.base.util.WebUtil;
import com.cosplay.login.entity.LoginUserEntity;
import com.cosplay.login.service.ILoginService;

/**登陆模块*/
@Controller
public class LoginAction {
	@Autowired
	private ILoginService loginService;
	
	/**Ajax 登陆用户*/
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public void userLogin(HttpServletRequest request,HttpServletResponse response,LoginUserEntity loginUser){
		JSONObject result = loginService.doLogin(request,response,loginUser);
		//登陆操作
		//LoginUserEntity entity = loginService.doLogin(request,response,loginUser);
		//JSONObject json = new JSONObject();
		//json.put(ClientConstants.RESULT,entity.getLoginState());
		WebUtil.writeJSON(response, result);
	}
	
	/**Ajax 用户注销*/
	@RequestMapping(value="/loginexit",method=RequestMethod.POST)
	public void userLogin(HttpServletRequest request,HttpServletResponse response){
		JSONObject result = loginService.exit(request,response);
		
		WebUtil.writeJSON(response, result);
	}
	
}
