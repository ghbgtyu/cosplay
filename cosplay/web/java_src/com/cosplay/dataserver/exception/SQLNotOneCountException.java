package com.cosplay.dataserver.exception;

import java.sql.SQLException;

public class SQLNotOneCountException extends SQLException {
	private static final long serialVersionUID = 435076660572407813L;
	public SQLNotOneCountException(){
		super();
	}
	public SQLNotOneCountException(String msg){
		super(msg);
	}
	public SQLNotOneCountException(Throwable cause){
		super(cause);
	}
	public SQLNotOneCountException(String msg, Throwable cause){
		super( msg, cause);
	}
}
