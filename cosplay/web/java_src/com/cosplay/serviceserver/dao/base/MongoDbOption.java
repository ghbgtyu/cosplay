package com.cosplay.serviceserver.dao.base;

import com.cosplay.serviceserver.dao.abstracts.AbsDbOption;


/**
 * 数据库参数
 * */
public class MongoDbOption extends AbsDbOption{
	private Integer writeConcern;
	private boolean upsert;
	private boolean multi;
	
	private String DbName;

	public Integer getWriteConcern() {
		return writeConcern;
	}

	public void setWriteConcern(Integer writeConcern) {
		this.writeConcern = writeConcern;
	}
	/**
	 * 如果没有找到需要更新的那一条，则插入一条数据
	 * */
	public boolean getUpsert() {
		return upsert;
	}

	public void setUpsert(boolean upsert) {
		this.upsert = upsert;
	}
	public boolean getMulti() {
		return multi;
	}
	/**
	 * 是否修改多条，为true时修改所有匹配的行,否则只修改第一条
	 * */
	public void setMulti(boolean multi) {
		this.multi = multi;
	}
	public String getDbName() {
		return DbName;
	}
	
	public void setDbName(String dbName) {
		DbName = dbName;
	}
	/**
	* Write operations that use this write concern will wait for acknowledgement from the primary server before returning.
    * Exceptions are raised for network issues, and server errors.
    * 写操作，等待至主服务器返回信息
    * 报网络异常以及服务器异常
    * */
	public static final Integer WRITECONCERN_ACKNOWLEDGED = 1; 
	/**
	 * Write operations that use this write concern will return as soon as the message is written to the socket.
     * Exceptions are raised for network issues, but not server errors.
     * 写操作，发出socket之后返回消息
     * 报网络异常但是不报服务器异常
	 * */
	public static final Integer WRITECONCERN_UNACKNOWLEDGED = 0;
	
	 /**
     * Exceptions are raised for network issues, and server errors; the write operation waits for the server to flush
     * the data to disk.
     * 等待至服务器刷新到硬盘
     */
	public static final Integer WRITECONCERN_FSYNCED = 100;
	/**
     * Exceptions are raised for network issues, and server errors; the write operation waits for the server to
     * group commit to the journal file on disk.
     * 等待至服务器提交到日志文件
     */	
	public static final Integer WRITECONCERN_JOURNALED = 101;
	
	/**
     * Exceptions are raised for network issues, and server errors; waits for at least 2 servers for the write operation.
     * 等待至少2太服务器写入数据
     */
	public static final Integer WRITECONCERN_REPLICA_ACKNOWLEDGED = 2;
	/**
     * Exceptions are raised for network issues, and server errors; waits on a majority of servers for the write operation.
     * 等待大多数服务器写操作结束
     */
	public static final Integer WRITECONCERN_MAJORITY =102;
	
	//-------------------------------------修改器
	/**
	 * 用于增加，
	 * db.analytics.update({"url","www.example"},{"$inc",{"pageviews":1}})
	 * **/
	public static final String INC = "$inc";
	/**用于修改一个字段的值，如果这个字段不存在，则创建它*/
	public static final String SET = "$set";
	/**
	 * 数组添加元素，向数组末端加入一个元素，如果没有就创一个新数组
	 * db.analytics.update({"id","GOOD"},{"$push",{"pageviews":1}})
	 * */
	public static final String PUSH = "$push";
	/**配合push，可以 添加多个值
	 * db.analytics.update({"id","GOOD"},{"$push",{"pageviews":{"$each":[1,2,3,4]}}})
	 * */
	public static final String EACH = "$each";
	/**用于跟数组结合，比如希望数组只有10个元素
	 * db.movies.find({"genre":"horror"},
	 * 	{"$push",{"top10":{
	 * 		"$each":["NIGHT","HHH"],
	 * 		"$slice":-10}}})
	 * */
	public static final String SLICE = "$slice";
	/**
	 * 排序所用 跟push、each、slice配合
	 * db.movies.find({"genre":"horror"},
	 * 	{"$push":{"top10",{
	 * 		"$each":[{"name":"Night","rating":6.6,
	 * 				  "name":"saw","rating":4.3}],
	 * 		"$slice":-10,
	 * 		"$sort":"rating":-1
	 * 	}}})
	 * */
	public static final String SORT = "$sort";
	/**
	 * 类似先查找有没有该项，没有的话，继续下一步
	 * db.papers.update("authors cites":{"$ne","Richie"},
	 * 	{"$push",{"authors cited","Richie"}})
	 * */
	public static final String NE = "$ne";
	/***
	 * 添加数据，可以避免重复数据
	 * */
	public static final String ADD_TO_SET = "$addToSet";
	/**
	 * 创建字段时为它赋值
	 * 
	 * */
	public static final String SET_ON_INSERT = "$setOnInsert";
	/**
	 * 删除数组中的元素
	 * {"$pop",-1} 从头部开始删除一个元素
	 * {"$pop",1} 从尾部开始删除一个元素
	 * */
	public static final String POP = "$pop";
	/**
	 * 删除匹配文档
	 * */
	public static final String PULL = "$pull";
	
	/**
	 * <
	 * */
	public static final String LT = "$lt";
	/**
	 * <=
	 * */
	public static final String LTE = "$lte";
	/**
	 * >
	 * */
	public static final String GT = "$gt";
	/**
	 * >=
	 * */
	public static final String GTE = "$gte";
	
	
	
}
