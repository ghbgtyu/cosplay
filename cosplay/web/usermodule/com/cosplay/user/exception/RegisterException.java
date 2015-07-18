package com.cosplay.user.exception;

public class RegisterException extends Exception{

	private static final long serialVersionUID = 7451563229516161720L;
	
	public RegisterException(){
		super("注册模块异常");
	}
	public RegisterException(String msg){
		super(msg);
	}
	
	@Override
	public String toString(){
		return super.toString();
	}

}
