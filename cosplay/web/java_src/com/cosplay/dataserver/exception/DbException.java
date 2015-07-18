package com.cosplay.dataserver.exception;


public class DbException extends RuntimeException {
	private static final long serialVersionUID = 435076660572407813L;
	public DbException(){
		super();
	}
	public DbException(String msg){
		super(msg);
	}
	public DbException(Throwable cause){
		super(cause);
	}
	public DbException(String msg, Throwable cause){
		super( msg, cause);
	}
}
