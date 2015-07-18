package com.cosplay.cache.exception;

/**
 * 没有注册缓存相关配置
 * **/
public class CacheNotRegisterException extends Exception {

	private static final long serialVersionUID = 6889062298712264884L;

	public CacheNotRegisterException(){
		super();
	}
	public CacheNotRegisterException(String msg){
		super(msg);
	}
	public CacheNotRegisterException(String msg ,Throwable cause){
		super(msg,cause);
	}
}
