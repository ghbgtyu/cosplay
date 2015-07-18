package com.cosplay.dataserver.db.mysql.impl;

import java.sql.Connection;

import com.cosplay.dataserver.db.base.interfaces.DbPool;

public class C3p0Impl implements DbPool{

	@Override
	public Connection getConn() {
		
		return null;
	}
	
}
