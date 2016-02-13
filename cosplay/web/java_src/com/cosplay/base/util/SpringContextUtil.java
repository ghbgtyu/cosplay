package com.cosplay.base.util;


import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationObjectSupport;

@Service
public class SpringContextUtil extends WebApplicationObjectSupport {
	
	
	private static final SpringContextUtil INSTANCE = new SpringContextUtil();
	
	public  ApplicationContext getApplication(){
		return super.getApplicationContext();
	}

	public static SpringContextUtil getInstance(){
		return INSTANCE;
	}
}
