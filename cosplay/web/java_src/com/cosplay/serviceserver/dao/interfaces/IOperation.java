package com.cosplay.serviceserver.dao.interfaces;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bson.types.ObjectId;

import com.cosplay.serviceserver.base.entity.IEntity;
import com.cosplay.serviceserver.dao.abstracts.AbsDbOption;

public interface IOperation <T extends IEntity,P extends AbsDbOption>{
	/**
	 * 增加一条记录，带数据库参数
	 * */
	boolean insert(T o,P op);
	
	/**批量插入,带数据库参数*/
	boolean batchInsert(List<T> listData, P op);
	
	/**修改记录*/
	boolean update(T query, T field, P op);
	/**查找一条记录,有排序*/
	T findOne(T query, T field, T orderBy,P op);
	/**查找一条记录*/
	T findOne(T query, T field,P op);
	/**查找一条记录*/
	T findOne(T query,P op);
	/**查找多条记录*/
	List<T> find(T query, T field,P op);
	/**删除记录*/
	boolean delete(T query,P op);
	/**删除记录*/
	boolean delete(String indexName,P op);
	/**插入一个文件*/
	ObjectId insertFile(File file, String namespace,P op) throws IOException;
	/**批量插入文件*/
	List<ObjectId> insertFiles(List<File> fileList, String namespace,P op)throws IOException;
	/**根据id查找文件名*/
	String findFileName(String namespace, ObjectId id,P op);
	/**根据idList查找对应的文件名集合*/
	List<String> findFileNames(String namespace, List<ObjectId> idList,P op);
	
	
	
	/**
	 * 
	 * 
	 * ----------------------------------------------默认参数版
	 * 增加一条记录，带数据库参数
	 * */
	boolean insert(T o);
	
	/**批量插入,带数据库参数*/
	boolean batchInsert(List<T> listData);
	
	/**修改记录*/
	boolean update(T query, T field);
	/**查找一条记录,有排序*/
	T findOne(T query, T field, T orderBy);
	/**查找一条记录*/
	T findOne(T query, T field);
	/**查找一条记录*/
	T findOne(T query);
	/**查找多条记录*/
	List<T> find(T query, T field);
	/**删除记录*/
	boolean delete(T query);
	/**删除记录*/
	boolean delete(String indexName);
	/**插入一个文件*/
	ObjectId insertFile(File file, String namespace) throws IOException;
	/**批量插入文件*/
	List<ObjectId> insertFiles(List<File> fileList, String namespace)throws IOException;
	/**根据id查找文件名*/
	String findFileName(String namespace, ObjectId id);
	/**根据idList查找对应的文件名集合*/
	List<String> findFileNames(String namespace, List<ObjectId> idList);



	
}
