package com.cosplay.user.exception.sub;

import com.cosplay.user.exception.RegisterException;


public class ExistUserException extends RegisterException {

	private static final long serialVersionUID = 8348426466018820703L;
	
	public ExistUserException(){
		super("用户已存在");
	}
	public ExistUserException(String msg){
		super(msg);
	}
	@Override
	public String toString(){
		return super.toString();
	}
}
