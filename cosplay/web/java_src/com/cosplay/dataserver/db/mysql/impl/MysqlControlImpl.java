package com.cosplay.dataserver.db.mysql.impl;

import java.sql.Connection;

import com.cosplay.dataserver.db.base.interfaces.DbBase;
import com.cosplay.dataserver.db.base.interfaces.DbPool;
/**
 * ��ȡ��ݿ�����ʵ����
 * */
public class MysqlControlImpl implements DbBase {
	private DbPool pool = new C3p0Impl();

	
	public Connection getConn() {
		
		return pool.getConn();
	}

}
