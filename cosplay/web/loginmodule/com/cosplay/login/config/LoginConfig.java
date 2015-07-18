package com.cosplay.login.config;

import com.cosplay.base.util.ConfigureUtil;
import com.cosplay.bus.config.BusConfig;

public class LoginConfig { 
	private final static String COOKIE_MAX_AGE = "cookie_max_age";
	
	public static int getCookieMaxAge(){
		return ConfigureUtil.getIntProp(BusConfig.LOCALIZATION_CONFIG_PATH, COOKIE_MAX_AGE);
	}
}
