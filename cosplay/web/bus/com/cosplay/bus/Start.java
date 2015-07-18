package com.cosplay.bus;

import com.cosplay.serviceserver.orm.core.OrmConvertorFactory;

public class Start {
	public static void main(String []args){
		System.out.println("start ----");
		OrmConvertorFactory.getInstance();
		System.out.println("end ----");
	}
}
