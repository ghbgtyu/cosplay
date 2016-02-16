package com.cosplay.user.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.cosplay.base.util.WebUtil;
import com.cosplay.bus.client.ClientConstants;
import com.cosplay.bus.code.ErrorCode;
import com.cosplay.check.handler.impl.EmailCheckHandler;
import com.cosplay.check.handler.impl.UserNameCheckHandler2;
import com.cosplay.check.model.NormalCheckResult;
import com.cosplay.check.service.ICheckService;
import com.cosplay.user.entity.UserEntity;
import com.cosplay.user.service.IUserService;


@Controller
public class UserAction {
	@Autowired
	private IUserService registerService;
	@Autowired
	private ICheckService checkService;
	
	/**
	 * 注册用户
	 * */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registerUser(HttpServletRequest request,UserEntity user){
		boolean result = false;
		result = registerService.registerUser(user);
		if ( result ){
			return "/usermodule/jsp/success";
			//注册成功，返回相关页面
		}else{
			//注册失败，返回注册页面
			return "/usermodule/jsp/error";
		}
	}
	/**Ajax 验证用户名*/
	@RequestMapping(value="/checkUserName",method=RequestMethod.POST,params="userLoginName")
	public void checkUserName(HttpServletRequest request,HttpServletResponse response,@RequestParam("userLoginName") String userLoginName){
		Boolean result = false;
		JSONObject json = new JSONObject();
		NormalCheckResult checkResult = new NormalCheckResult();
		checkService.check(new EmailCheckHandler(checkResult,userLoginName));
		if(checkResult.isSuccess()){
			if ( registerService.checkUserNameIsExist(userLoginName)){
				result = true;
			}else{
				//用户已存在
				json.put(ClientConstants.ERROR_CODE, ErrorCode.ERROR_1004);
			}
		}else{
			//不是email格式的用户
			json.put(ClientConstants.ERROR_CODE,checkResult.getErrorCode());
		}
		json.put(ClientConstants.RESULT, result);
		WebUtil.writeJSON(response, json);
	};
	
	/**Ajax 验证昵称*/
	@RequestMapping(value="/checkUserName",method=RequestMethod.POST,params="userCosName")
	public void checkUserCosName(HttpServletRequest request,HttpServletResponse response,@RequestParam("userCosName") String userCosName){
		Boolean result = false;
		JSONObject json = new JSONObject();
		NormalCheckResult checkResult = new NormalCheckResult();
		checkService.check(new UserNameCheckHandler2(checkResult,userCosName));
		if(checkResult.isSuccess()){
			if ( registerService.checkUserCosNameIsExist(userCosName)){
				result = true;
			}else{
				//昵称已存在
				json.put(ClientConstants.ERROR_CODE, ErrorCode.ERROR_1006);
			}
		}else{
			//昵称格式不对
			json.put(ClientConstants.ERROR_CODE,checkResult.getErrorCode());
		}
		json.put(ClientConstants.RESULT, result);
		WebUtil.writeJSON(response, json);
	};
}
