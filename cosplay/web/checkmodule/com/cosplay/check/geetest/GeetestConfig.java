package com.cosplay.check.geetest;

/**
 * GeetestWeb配置文件
 * 
 *
 */
public class GeetestConfig {

	// 填入自己的captcha_id和private_key
	private static final String captcha_id = "40b362baaec17a418bc2c90097bccfed";
	private static final String private_key = "4828fd2c5be150518e367e7439dabad7";

	public static final String getCaptcha_id() {
		return captcha_id;
	}

	public static final String getPrivate_key() {
		return private_key;
	}

}
