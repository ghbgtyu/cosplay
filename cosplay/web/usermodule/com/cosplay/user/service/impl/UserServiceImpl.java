package com.cosplay.user.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cosplay.bus.client.ClientConstants;
import com.cosplay.bus.code.ErrorCode;
import com.cosplay.bus.event.EventPublicUtil;
import com.cosplay.bus.log.CosplayLog;
import com.cosplay.check.handler.impl.EmailCheckHandler;
import com.cosplay.check.handler.impl.GeetestCheckHandler;
import com.cosplay.check.handler.impl.PwdCheckHandler;
import com.cosplay.check.handler.impl.UserNameCheckHandler;
import com.cosplay.check.model.NormalCheckResult;
import com.cosplay.check.service.ICheckService;
import com.cosplay.user.dao.IUserDao;
import com.cosplay.user.entity.UserEntity;
import com.cosplay.user.event.publish.RegisterEvent;
import com.cosplay.user.service.IUserService;

@Component
public class UserServiceImpl implements IUserService{
	@Autowired
	private IUserDao registerUserDao;
	@Autowired
	private UserContext userContext;
	@Autowired
	private ICheckService checkService;
	
	
	@Override
	public JSONObject registerUser(UserEntity user,HttpServletRequest request){
		JSONObject result = null;
		try{
			result = checkRegisterUser(user, request);
			
			if(result !=null){
				return result;
			}
			
			// 进行注册用户
			boolean success = registerUserDao.insert(user);
			EventPublicUtil.publish(new RegisterEvent(user.getUserCosName()));
			result = new JSONObject();
			if(success){
				result.put(ClientConstants.RESULT, true);
			}else{
				result.put(ClientConstants.RESULT, false);
				result.put(ClientConstants.ERROR_CODE, ErrorCode.ERROR_1000);
			}
		}catch(Exception e){
			CosplayLog.error("注册用户异常", e);
			result = new JSONObject();
			result.put(ClientConstants.RESULT, false);
			result.put(ClientConstants.ERROR_CODE, ErrorCode.ERROR_1000);
		}
		
		
		return result;
	}
	
	private JSONObject checkRegisterUser(UserEntity user,HttpServletRequest request) {
		JSONObject result = new JSONObject();
		NormalCheckResult checkResult = new NormalCheckResult();
		checkService.check(
				new GeetestCheckHandler(checkResult, request),
				new EmailCheckHandler(checkResult,user.getUserLoginName()),
				new UserNameCheckHandler(checkResult,user.getUserCosName()),
				new PwdCheckHandler(checkResult,user.getUserPassWord()));
		if(!checkResult.isSuccess()){
			result.put(ClientConstants.RESULT, false);
			result.put(ClientConstants.ERROR_CODE, checkResult.getErrorCode());
			return result;
		}
		// 检查用户名
		if (!checkUserNameIsExist(user.getUserLoginName())) {
			result.put(ClientConstants.RESULT, false);
			result.put(ClientConstants.ERROR_CODE, ErrorCode.ERROR_1004);
			return result;
		}
		// 检查昵称(cn
		if (!checkUserCosNameIsExist(user.getUserCosName())) {
			result.put(ClientConstants.RESULT, false);
			result.put(ClientConstants.ERROR_CODE,  ErrorCode.ERROR_1006);
			return result;
		}
		return null;
	}

	/**如果存在用户，返回false*/
	@Override
	public boolean checkUserNameIsExist(String userLoginName){
		Boolean result = false;
		try{
			//从缓存寻找是否存在用户
			if( userContext.userIdisExist(userLoginName)){
				return false;
			}
			UserEntity user = new UserEntity();
			user.setUserLoginName(userLoginName);
			result =  registerUserDao.findOne(user)==null? true:false;
			if( !result ){
				//把该用户名存到缓存
				userContext.putRegisterUserIdByKey(userLoginName, userLoginName);
			}
		}catch(Exception e){
			CosplayLog.error(e.getMessage());
			result = false;
		}
		return result;
	}
	/**如果存在昵称，返回false*/
	@Override
	public boolean checkUserCosNameIsExist(String userCosName){
		Boolean result = false;
		try{
			//从缓存寻找是否存在用户昵称
			if( userContext.userNameisExist(userCosName)){
				return false;
			}
			UserEntity user = new UserEntity();
			user.setUserCosName(userCosName);
			result = registerUserDao.findOne(user)==null? true:false;
			if( !result ){
				//把该用户昵称存到缓存
				userContext.putRegisterUserNameByKey(userCosName, userCosName);
			}
		}catch(Exception e){
			CosplayLog.error("未知异常"+e);
			result = false;
		}
		return result;
	}

	@Override
	public boolean checkUserPassword(String userLoginName, String userPassWord) {
		Boolean result = false;
		try{
			UserEntity user = new UserEntity();
			user.setUserLoginName(userLoginName);
			user.setUserPassWord(userPassWord);
			result = registerUserDao.findOne(user)==null? true:false;
		}catch(Exception e){
			CosplayLog.error("未知异常"+e);
			result = false;
		}
		return result;
	}

	@Override
	public UserEntity findUser(String userLoginName, String userPassWord) {
		UserEntity resultUser = null;
		try{
			UserEntity userQuery = new UserEntity();
			userQuery.setUserLoginName(userLoginName);
			userQuery.setUserPassWord(userPassWord);
			resultUser =  registerUserDao.findOne(userQuery);
		}catch(Exception e){
			CosplayLog.error("未知异常"+e);
		}
		return resultUser;
	}

}
