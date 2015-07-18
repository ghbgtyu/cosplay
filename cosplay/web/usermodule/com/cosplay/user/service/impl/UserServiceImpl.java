package com.cosplay.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cosplay.bus.event.EventPublicUtil;
import com.cosplay.bus.log.CosplayLog;
import com.cosplay.user.dao.IUserDao;
import com.cosplay.user.entity.UserEntity;
import com.cosplay.user.event.publish.RegisterEvent;
import com.cosplay.user.exception.sub.ExistUserException;
import com.cosplay.user.service.IUserService;

@Component
public class UserServiceImpl implements IUserService{
	@Autowired
	private IUserDao registerUserDao;
	@Autowired
	private UserContext userContext;

	@Override
	public boolean registerUser(UserEntity user){
		boolean result = false;
		try{
			//检查用户名
			if(!checkUserNameIsExist(user.getUserLoginName())){
				throw new ExistUserException();
			}
			//检查昵称(cn
			if(!checkUserCosNameIsExist(user.getUserCosName())){
				throw new ExistUserException();
			}
			//进行注册用户
			result = registerUserDao.insert(user);
			EventPublicUtil.publish(new RegisterEvent(user.getUserCosName()));
		}catch(ExistUserException e){
			return false;
		}catch(Exception e){
			CosplayLog.error("未知异常"+e);
			return false;
		}
		return result;
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
