package com.cosplay.serviceserver.base.context;

public class ServiceApplicationContext {
	public String ENTITY_PACKAGE_PATH = "com.cosplay";
	
	
	
	
	
	private static ServiceApplicationContext context= new ServiceApplicationContext();
	public static ServiceApplicationContext getInstance(){
		return context;
	}
}
