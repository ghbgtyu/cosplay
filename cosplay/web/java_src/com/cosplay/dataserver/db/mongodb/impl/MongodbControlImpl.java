package com.cosplay.dataserver.db.mongodb.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.cosplay.bus.log.CosplayLog;
import com.cosplay.dataserver.bean.MongoDbBean;
import com.cosplay.dataserver.context.DataApplicationContext;
import com.cosplay.dataserver.db.base.interfaces.DbBase;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.MongoServerSelectionException;
import com.mongodb.ServerAddress;

public class MongodbControlImpl implements DbBase {
	private MongoClient mongoClient = null;
	private static MongodbControlImpl mongodbControlImpl =new  MongodbControlImpl();
	/**
	 * 已连接上的DB
	 * */
	protected List<String> dbList;
	public static MongodbControlImpl getInstance(){
		return mongodbControlImpl;
	}
	private  MongodbControlImpl(){
		Init();
	}
	private void Init(){
		try{
			mongoClient = mongoClientInit(DataApplicationContext.getInstance().mongoDbList);
			//尝试连接已注册的DB
			for(MongoDbBean regidterDb:DataApplicationContext.getInstance().mongoDbList){
				DB db = mongoClient.getDB(regidterDb.getDatabase());
				if( dbList == null ){
					dbList = new ArrayList<String>();
				}
				dbList.add(db.getName());
			}
			if( dbList.size() ==0 ){
				throw new MongoServerSelectionException("错误，一个DB都没有连接到");
			}
		}catch(MongoException e){
			CosplayLog.error("数据库连接失败", e);
			throw new RuntimeException();
		}
		
		//authenticateDbInit();
	}
	/**
	 * 用户认证
	 * */
/*	private boolean authenticateDbInit(){
		boolean flag = false;
		if( mongoClient!=null ){
			List<MongoCredential> mongoCredentiaList = mongoClient.getCredentialsList();
			for(MongoCredential mongoCredential:mongoCredentiaList){
				//该方法马上要废弃，目前还没发现其他方法来验证
				flag = mongoClient.getDB(mongoCredential.getSource()).authenticate(mongoCredential.getUserName(), mongoCredential.getPassword());
				if( !flag ){
					log.error("警告!用户验证没有通过:DB Name:"+mongoCredential.getSource());
				}
			}
			
		}else{
			return flag;
		}
		return flag;
	}*/
	private MongoClient mongoClientInit(List<MongoDbBean> mongoBbBeanList) {
 		List<ServerAddress> serverAddressList = new ArrayList<ServerAddress>();
 		List<MongoCredential>mongoCredentialList = new ArrayList<MongoCredential>();
		Iterator <MongoDbBean>it = mongoBbBeanList.iterator();
		while(it.hasNext()){
			MongoDbBean mongoDbBean = it.next();
			ServerAddress serverAddress = null;
			try {
				serverAddress = new ServerAddress(mongoDbBean.getLocalAddress(),mongoDbBean.getPort());
				mongoCredentialList.add(MongoCredential.createMongoCRCredential(mongoDbBean.getUserName(),mongoDbBean.getDatabase(), mongoDbBean.getPassWord().toCharArray()));
			} catch (UnknownHostException e) {
				CosplayLog.error("mongodb数据库连接初始化失败"+e);
				throw new RuntimeException("mongodb数据库连接初始化失败",e);
			}
			if(serverAddress!=null){
				serverAddressList.add(serverAddress);
			}
			
		}
		return new MongoClient(serverAddressList,mongoCredentialList);
	}
	public MongoClient getMongoClient() {
		return mongoClient;
	}
	public DB getDb(){
		return mongoClient.getDB(dbList.get(0));
	}
	public DB getDb(String dbName){
		if( !dbList.contains(dbName) ){
			CosplayLog.error("没有注册这个DB:",dbName);
			return null;
		}
		return mongoClient.getDB(dbName);
	}
}
