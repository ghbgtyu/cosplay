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
import com.cosplay.user.constants.UserConstants;
import com.cosplay.user.entity.UserEntity;
import com.cosplay.user.service.IUserService;


@Controller
public class UserAction {
	@Autowired
	private IUserService registerService;
	
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
		if ( registerService.checkUserNameIsExist(userLoginName)){
			result = true;
		}
		JSONObject json = new JSONObject();
		json.put(UserConstants.AJAX_CHECK_USER_RESULT, result);
		WebUtil.writeJSON(response, json);
	};
	
	/**Ajax 验证昵称*/
	@RequestMapping(value="/checkUserName",method=RequestMethod.POST,params="userCosName")
	public void checkUserCosName(HttpServletRequest request,HttpServletResponse response,@RequestParam("userCosName") String userCosName){
		Boolean result = false;
		if ( registerService.checkUserCosNameIsExist(userCosName)){
			result = true;
		}
		JSONObject json = new JSONObject();
		json.put(UserConstants.AJAX_CHECK_USER_RESULT, result);
		WebUtil.writeJSON(response, json);
	};
}
