package com.cosplay.dataserver.export;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.cosplay.dataserver.db.mongodb.dao.MongodbDao;
import com.cosplay.dataserver.server.DbBasePoolManager;
import com.cosplay.serviceserver.dao.base.MongoDbOption;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.WriteConcern;

/**
 * data服务器对外接口
 * */
public class DataOutputServiceByMongo {
	private static DbBasePoolManager dbManager = DbBasePoolManager.getInstance();
	public static boolean insert(Map<String,Object>data,String collection,MongoDbOption option){
		DB db = getDb(option);
		return MongodbDao.insert(db, convertor(data), collection, getWriteConcern(option));
	}

	public static boolean batchInsert(List<Map<String,Object>> data,String collection,MongoDbOption option){
		DB db = getDb(option);
		return MongodbDao.insert(db, convertor(data), collection, getWriteConcern(option));
	}
	
	public static boolean update(Map<String,Object>query,Map<String,Object>field,String collection,MongoDbOption option){
		DB db = getDb(option);
		return MongodbDao.update(db, convertor(query), convertor(field), collection, getWriteConcern(option), option.getUpsert(), option.getMulti(),null);
	}
	public static Map findOne(Map<String,Object>query,Map<String,Object>field,Map<String,Object>orderBy,String collection,MongoDbOption option){
		DB db = getDb(option);
		return MongodbDao.findOne(db,collection, convertor(query), convertor(field), convertor(orderBy));
	}
	public static Map findOne(Map<String,Object>query,Map<String,Object>field,String collection,MongoDbOption option){
		DB db = getDb(option);
		return MongodbDao.findOne(db,collection, convertor(query), convertor(field));
	}
	public static Map findOne(Map<String,Object>query,String collection,MongoDbOption option){
		DB db = getDb(option);
		return MongodbDao.findOne(db,collection, convertor(query));
	}
	
	public static List<Map>find(Map<String,Object>query,Map<String,Object>field,String collection,MongoDbOption option){
		DB db = getDb(option);
		return MongodbDao.find(db,collection, convertor(query), convertor(field));
	}
	public static boolean delete(Map<String,Object>data,String collection,MongoDbOption option){
		DB db = getDb(option);
		return MongodbDao.delete(db, convertor(data), collection);
	}
	public static boolean delete(String indexName,String collection,MongoDbOption option){
		DB db = getDb(option);
		return MongodbDao.delete(db,indexName, collection);
	}
	public static ObjectId insertFile(File file,String namespace,MongoDbOption option) throws IOException{
		DB db = getDb(option);
		return MongodbDao.insertFile(db, file, namespace);
	}
	public static List<ObjectId> insertFiles(List<File> fileList,String namespace,MongoDbOption option) throws IOException{
		DB db = getDb(option);
		return MongodbDao.insertFileList(db, fileList, namespace);
	}
	
	public static String findFileName(String namespace,ObjectId id,MongoDbOption option){
		DB db = getDb(option);
		return MongodbDao.findFileName(db,id, namespace);
	}
	public static List<String>findFileNames(String namespace,List<ObjectId> idList,MongoDbOption option){
		DB db = getDb(option);
		return MongodbDao.findFileNames(db, idList, namespace);
	}
	private static DBObject convertor(Map<String,Object>data){
		if( data == null ){
			return null;
		}
		return new BasicDBObject(data);
	}
	protected static DB getDb(MongoDbOption option){
		DB db = option.getDbName()==null?dbManager.getMongoDb():dbManager.getMongoDb(option.getDbName().trim());
		return db;
	}
	private static List<DBObject> convertor (List<Map<String,Object>> datas){
		List<DBObject> dataList = new ArrayList<DBObject>();
		for(Map<String,Object>data:datas){
			dataList.add(convertor(data));
		}	
		return dataList;
	}
	private static WriteConcern getWriteConcern(MongoDbOption writeConcern){
		if( writeConcern.getWriteConcern() !=null ){
			switch( writeConcern.getWriteConcern() ){
			case 0:return WriteConcern.UNACKNOWLEDGED;
			case 1:return WriteConcern.ACKNOWLEDGED;
			case 2:return WriteConcern.REPLICA_ACKNOWLEDGED;
			case 100:return WriteConcern.FSYNCED;
			case 101:return WriteConcern.JOURNALED;
			case 102:return WriteConcern.MAJORITY;
			}
		}
		return WriteConcern.ACKNOWLEDGED;
	}
}
