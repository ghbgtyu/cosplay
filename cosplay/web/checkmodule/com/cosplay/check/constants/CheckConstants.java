package com.cosplay.check.constants;

public class CheckConstants {
	/**email 格式用户*/
	public static final String MATCH_USER_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
	/**密码正则，6-20位 字母或者数字*/
	public static final String MATCH_PASS_WORLD = "[0-9a-zA-Z]{6,20}";
	
	/**昵称正则*/
	public static final String MATCH_USER_NAME = "^[A-Za-z0-9_\u4E00-\u9FA5]{1,16}$";
	
	
}
