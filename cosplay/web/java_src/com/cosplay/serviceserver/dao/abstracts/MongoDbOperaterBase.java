package com.cosplay.serviceserver.dao.abstracts;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cosplay.dataserver.export.DataOutputServiceByMongo;
import com.cosplay.serviceserver.base.entity.IEntity;
import com.cosplay.serviceserver.dao.base.MongoDbOption;
import com.cosplay.serviceserver.dao.interfaces.IOperation;
import com.cosplay.serviceserver.orm.core.OrmConvertorFactory;
import com.cosplay.serviceserver.orm.core.interfaces.OrmConvertor;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import org.bson.types.ObjectId;


public abstract class MongoDbOperaterBase<T extends IEntity> implements IOperation<T,MongoDbOption> {
	private static final OrmConvertor converter = OrmConvertorFactory.getInstance().getConvertor();
	private static final Map<Class<? extends Object>,String>collectionMap =OrmConvertorFactory.getInstance().getCollectionMap();
	private static final MongoDbOption defaultOption ;
	@SuppressWarnings("unchecked")
	private Class<T> poClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	static{
		defaultOption = new MongoDbOption();
		defaultOption.setWriteConcern(MongoDbOption.WRITECONCERN_ACKNOWLEDGED);
	}
	@Override
	public  boolean insert(T o ,MongoDbOption op){
		return DataOutputServiceByMongo.insert(converter.convertorDbData(o),collectionMap.get(poClass),op);
	}
	@Override
	public  boolean insert(T o){
		return insert(o,defaultOption);
	}
	
	@Override
	public boolean batchInsert(List<T>listData,MongoDbOption op){
		return DataOutputServiceByMongo.batchInsert(converterToListMap(listData), collectionMap.get(poClass), op);
	}
	@Override
	public boolean batchInsert(List<T>listData){
		return batchInsert(listData, defaultOption);
	}
	
	@Override
	public boolean update(T query,T field,MongoDbOption op){
		return DataOutputServiceByMongo.update(converter.convertorDbData(query), converter.convertorDbData(field), collectionMap.get(poClass), op);
	}
	@Override
	public boolean update(T query,T field){
		return update(query,field,defaultOption);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T findOne(T query,T field,T orderBy,MongoDbOption op){
		return (T)converter.convertorObjFromDbData(DataOutputServiceByMongo.findOne(converter.convertorDbData(query),converter.convertorDbData(field),converter.convertorDbData(orderBy), collectionMap.get(poClass),op),poClass);
	}
	@Override
	public T findOne(T query,T field,T orderBy){
		return findOne(query,field,orderBy,defaultOption);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T findOne(T query,MongoDbOption op){
		return (T)converter.convertorObjFromDbData(DataOutputServiceByMongo.findOne(converter.convertorDbData(query), collectionMap.get(poClass),op),poClass);
	}
	@Override
	public T findOne(T query){
		return findOne(query,defaultOption);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public T findOne(T query,T field,MongoDbOption op){
		return (T)converter.convertorObjFromDbData(DataOutputServiceByMongo.findOne(converter.convertorDbData(query),converter.convertorDbData(field), collectionMap.get(poClass),op),poClass);
	}
	@Override
	public T findOne(T query,T field){
		return findOne(query,field,defaultOption);
	}
	
	
	@Override
	public List<T> find(T query,T field,MongoDbOption op){
		List<Map> resultMap = DataOutputServiceByMongo.find(converter.convertorDbData(query), converter.convertorDbData(field),collectionMap.get(poClass),op);
		if( resultMap == null ){
			return null;
		}
		return converterToListEntity(resultMap);
	}
	@Override
	public List<T> find(T query,T field){
		return find(query,field,defaultOption);
	}
	
	@Override
	public boolean delete(T query,MongoDbOption op){
		return DataOutputServiceByMongo.delete(converter.convertorDbData(query), collectionMap.get(poClass),op);
	}
	@Override
	public boolean delete(T query){
		return delete(query,defaultOption);
	}
	
	@Override
	public boolean delete(String indexName,MongoDbOption op){
		return DataOutputServiceByMongo.delete(indexName, collectionMap.get(poClass),op);
	}
	@Override
	public boolean delete(String indexName){
		return delete(indexName,defaultOption);
	}
	
	
	@Override
	public  ObjectId insertFile(File file,String namespace,MongoDbOption op) throws IOException {
		return DataOutputServiceByMongo.insertFile(file, namespace,op);
	}
	@Override
	public  ObjectId insertFile(File file,String namespace) throws IOException {
		return insertFile(file,namespace,defaultOption);
	}
	
	
	@Override
	public  List<ObjectId> insertFiles(List<File> fileList,String namespace,MongoDbOption op) throws IOException{
		return DataOutputServiceByMongo.insertFiles(fileList, namespace,op);
	}
	@Override
	public  List<ObjectId> insertFiles(List<File> fileList,String namespace) throws IOException{
		return insertFiles(fileList,namespace,defaultOption);
	}
	
	@Override
	public  String findFileName(String namespace,ObjectId id,MongoDbOption op){
		return DataOutputServiceByMongo.findFileName(namespace, id,op);
	}
	@Override
	public  String findFileName(String namespace,ObjectId id){
		return findFileName(namespace,id,defaultOption);
	}
	
	@Override
	public  List<String>findFileNames(String namespace,List<ObjectId> idList,MongoDbOption op){
		return DataOutputServiceByMongo.findFileNames(namespace, idList,op);
	}
	@Override
	public  List<String>findFileNames(String namespace,List<ObjectId> idList){
		return findFileNames(namespace,idList,defaultOption);
	}
	
	
	@SuppressWarnings("unchecked")
	protected List<T>converterToListEntity(List<Map> ListMapData){
		List<T> listEntityData = new ArrayList<T>();
		for(Map mapData:ListMapData){
			listEntityData.add((T)converter.convertorObjFromDbData(mapData,poClass));
		}
		return listEntityData;
	}
	protected List<Map<String,Object>> converterToListMap(List<T>listData){
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
		for(T t :listData){
			Map<String,Object> result = converter.convertorDbData(t);
			if( result!=null ){
				listMap.add(result);
			}
		}
		return listMap;
	}
	
	
	public static void main(String[]args){
		MongoDbOption option = new MongoDbOption();
		option.setWriteConcern(MongoDbOption.WRITECONCERN_ACKNOWLEDGED);
		long nowtime = System.currentTimeMillis();
		for(int i = 0 ;i<1000;i++){
		/*	EntityClub e = new EntityClub();
			e.setClubId(i);
			e.setClubMessage("nijia"+i);*/
			//MongoDbOperaterBase.getInstance().insert(e,option);
		}
		long lasttime = System.currentTimeMillis();
		System.out.println((lasttime-nowtime));
	}
}
