package com.cosplay.dataserver.db.mongodb.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.cosplay.bus.log.CosplayLog;
import com.cosplay.dataserver.exception.DbException;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBEncoder;
import com.mongodb.DBObject;
import com.mongodb.MongoClientException;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class MongodbDao {

	
	public static boolean insert(DB db, DBObject data,String collection,WriteConcern writeConcern) {
		boolean result = false;
		try{
			result = db.getCollection(collection).insert(data,writeConcern).getLastConcern().callGetLastError();
		}catch(MongoClientException e){
			CosplayLog.error("数据库操作失败", e);
		}
		return result;
	}
	public static boolean insert(DB db,DBObject[]data,String collection, WriteConcern writeConcern ) {
		boolean result = false;
		try{
			result= db.getCollection(collection).insert(data,writeConcern).getLastConcern().callGetLastError();
		}catch(MongoClientException e){
			CosplayLog.error("数据库操作失败", e);
		}
		
		return result;
	}
	public static boolean insert(DB db, List<DBObject>data,String collection,WriteConcern writeConcern ) {
		boolean result = false;
		try{
			result= db.getCollection(collection).insert(data,writeConcern).getLastConcern().callGetLastError();
		}catch(MongoClientException e){
			CosplayLog.error("数据库操作失败", e);
		}
		return result;
	}
	public static boolean insert(DB db, WriteConcern writeConcern ,String collection,DBObject ...data) {
		boolean result = false;
		try{
			result= db.getCollection(collection).insert(data,writeConcern).getLastConcern().callGetLastError();
		}catch(MongoClientException e){
			CosplayLog.error("数据库操作失败", e);
		}
		return result;
	}
	public static boolean insert(DB db,DBObject[]data,String collection, WriteConcern writeConcern ,DBEncoder encoder) {
		boolean result = false;
		try{
			result= db.getCollection(collection).insert(data, writeConcern, encoder).getLastConcern().callGetLastError();
		}catch(MongoClientException e){
			CosplayLog.error("数据库操作失败", e);
		}
		return result;
	}
	public static boolean insert(DB db,List<DBObject>data,String collection, WriteConcern writeConcern ,DBEncoder encoder) {
		boolean result = false;
		try{
			result= db.getCollection(collection).insert(data, writeConcern, encoder).getLastConcern().callGetLastError();
		}catch(MongoClientException e){
			CosplayLog.error("数据库操作失败", e);
		}
		return result;
	}
	
	
/*	public static boolean update(DB db, DBObject data, DBObject updateData,String collection) {
		return MongodbDao.update(db, data, updateData, collection, WriteConcern.ACKNOWLEDGED, false, false, null);
	}
	public static boolean update(DB db, DBObject data, DBObject updateData,String collection,boolean upsert,boolean multi) {
		return MongodbDao.update(db, data, updateData, collection, WriteConcern.ACKNOWLEDGED, upsert, multi, null);
	}
	public static boolean update(DB db, DBObject data, DBObject updateData,String collection,WriteConcern concern,boolean upsert,boolean multi) {
		return MongodbDao.update(db, data, updateData, collection, concern, upsert, multi, null);
	}*/
	public static boolean update(DB db, DBObject data, DBObject updateData,String collection,WriteConcern concern,boolean upsert,boolean multi,DBEncoder encoder) {
		boolean result = false;
		try{
			DBCollection c = db.getCollection(collection);
			WriteResult w =c.update(data, updateData, upsert, multi, concern, encoder);
			if( w.getLastConcern().callGetLastError() ){
				if( w.getN()>0 ){
					result =  true;
				}else{
					result =  false;
				}
			}else{
				result =  false;
			}
		}catch(MongoClientException e){
			CosplayLog.error("数据库操作失败", e);
		}
		return result;
	}
	
	public static boolean delete(DB db, DBObject data, String collection) {
		boolean result = false;
		try{
			db.getCollection(collection).dropIndex(data);
		}catch(MongoClientException e){
			result = false;
			CosplayLog.error("数据库操作失败", e);
		}
		return result;
	}
	public static boolean delete(DB db, String indexName, String collection) {
		boolean result = false;
		try{
			db.getCollection(collection).dropIndex(indexName);
		}catch(MongoClientException e){
			result = false;
			CosplayLog.error("数据库操作失败", e);
		}
		return result;
	}
	public static boolean deleteAll(DB db,String collection) {
		boolean result = false;
		try{
			db.getCollection(collection).dropIndexes();
		}catch(MongoClientException e){
			result = false;
			CosplayLog.error("数据库操作失败", e);
		}
		
		return result;
	}
	public static Map findOne(DB db,String collection,DBObject o,DBObject fields,DBObject orderBy){
		DBObject data =null;
		try{
			data = db.getCollection(collection).findOne(o, fields, orderBy);
		}catch(MongoClientException e){
			CosplayLog.error("数据库操作失败", e);
		}
		
		return data!=null? data.toMap():null;
	}
	public static Map findOne(DB db,String collection,DBObject o,DBObject fields){
		DBObject data =null;
		try{
			data = db.getCollection(collection).findOne(o, fields);
		}catch(MongoClientException e){
			CosplayLog.error("数据库操作失败", e);
		}
		return data!=null? data.toMap():null;
	}
	public static Map findOne(DB db,String collection,DBObject o){
		DBObject data =null;
		try{
			data = db.getCollection(collection).findOne(o);
		}catch(MongoClientException e){
			CosplayLog.error("数据库操作失败", e);
		}
		return data!=null? data.toMap():null;
	}
	public static List<Map> find(DB db,String collection,DBObject o,DBObject fields){
		List<Map> dataList = null;
		try{
			DBCursor data = db.getCollection(collection).find(o, fields);
			if( data == null){
				return null;
			}
			Iterator<DBObject> it = data.iterator();
			dataList = new ArrayList<Map>();
			while (it.hasNext()){
				dataList.add(it.next().toMap());
			}
		}catch(MongoClientException e){
			CosplayLog.error("数据库操作失败", e);
		}
		return dataList;
	}
	//------------------------------------------------------文件处理
	/**插入一个文件
	 * @return 图片id
	 * @throws IOException 
	 * */
	public static ObjectId insertFile(DB db, File file,String namespace) throws IOException{
		ObjectId objId = null;
		try{
			GridFS gridFs = new GridFS(db,namespace);
			GridFSInputFile gridFsInputFile = gridFs.createFile(file);
			objId = (ObjectId)gridFsInputFile.getId();
		}catch(MongoClientException e){
			CosplayLog.error("数据库操作失败", e);
		}
		return objId;
	}
	/**插入多个文件
	 * @return 图片id
	 * @throws IOException 
	 * */
	public static List<ObjectId> insertFileList(DB db,List<File>fileList,String namespace) throws IOException{
		List<ObjectId> idList = null;
		try{
			GridFS gridFs = new GridFS(db,namespace);
			if( fileList!=null ){
				for(File file:fileList){
					if( idList == null ){
						idList = new ArrayList<ObjectId>();
					}
					GridFSInputFile gridFsInputFile = gridFs.createFile(file);
					idList.add((ObjectId)gridFsInputFile.getId());
				}
			}
		}catch(MongoClientException e){
			CosplayLog.error("数据库操作失败", e);
		}
		return idList;
	}
	/**根据ID查询文件名字
	 * @param id 唯一ID
	 * */
	public static  String findFileName(DB db,ObjectId id,String namespace){
		String fileName =null;
		try{
			GridFS gridFs = new GridFS(db,namespace);
			GridFSDBFile  gridFsDbFile = gridFs.find(id);
			if( gridFsDbFile == null ){
				return null;
			}
			fileName = gridFsDbFile.getFilename();
		}catch(MongoClientException e){
			CosplayLog.error("数据库操作失败", e);
		}
		return fileName;
	}
	
	/**根据ID查询文件名字
	 * @param id 唯一ID
	 * */
	public static  List<String> findFileNames(DB db,List<ObjectId> idList,String namespace){
		List<String> nameList = null;
		try{
			GridFS gridFs = new GridFS(db,namespace);
			if( idList == null ){
				return nameList;
			}
			if( nameList == null ){
				nameList = new ArrayList<String>();
			}
			for( ObjectId id:idList ){
				GridFSDBFile  gridFsDbFile = gridFs.find(id);
				if( gridFsDbFile == null ){
					continue;
				}
				nameList.add(gridFsDbFile.getFilename());
			}
		}catch(MongoClientException e){
			CosplayLog.error("数据库操作失败", e);
		}
		return nameList;
	}
}
