package com.cosplay.dataserver.type;

public enum DataBaseNameEnum {
	MYSQL("mysql"),MONGODB("mongodb");
	private final String name;
	private DataBaseNameEnum(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
}
