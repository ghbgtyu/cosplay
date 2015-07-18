package com.cosplay.user.test;


import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cosplay.user.action.UserAction;
import com.cosplay.user.dao.IUserDao;
import com.cosplay.user.dao.impl.UserDaoImpl;
import com.cosplay.user.entity.UserEntity;
import com.cosplay.user.exception.RegisterException;
import com.cosplay.user.service.IUserService;
import com.cosplay.user.service.impl.UserServiceImpl;


public class RegisterUserTest {

	@Test
	public void test() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-servlet.xml");
		IUserDao userDao = context.getBean(UserDaoImpl.class);
		
		UserEntity user = new UserEntity();
		user.setUserLoginName("niji");
		UserEntity user2  = userDao.findOne(user);
		System.out.println(user2.getId());
		UserEntity user3 = new UserEntity();
		user3.setId(user2.getId());
		UserEntity user4 = userDao.findOne(user3);
		System.out.println(user4.getId().toString());
		StringBuffer s = new StringBuffer();
	}

}
