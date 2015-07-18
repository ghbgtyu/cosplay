package com.cosplay.dataserver.db.base.interfaces;

import java.sql.Connection;

/**
 * 数据库连接池接口
 * */
public interface DbPool {
	//获取数据库连接
	public Connection getConn();
}
