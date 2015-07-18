package com.cosplay.dataserver.context;

import java.util.List;

import com.cosplay.dataserver.bean.MongoDbBean;
import com.cosplay.dataserver.cfg.DataApplicationContextCfg;
import com.cosplay.dataserver.type.DataBaseNameEnum;

/**
 * 数据层上下文
 * */
public class DataApplicationContext {
	public final DataBaseNameEnum MYSQL  = DataBaseNameEnum.MYSQL;
	public final DataBaseNameEnum MONGODB = DataBaseNameEnum.MONGODB;
	public final List<MongoDbBean> mongoDbList = DataApplicationContextCfg.getInstance().getMongoDbBeanList();
	private static DataApplicationContext context = new DataApplicationContext();
	
	private DataApplicationContext(){}
	public static DataApplicationContext getInstance(){
		return context;
	}
	
	
}
