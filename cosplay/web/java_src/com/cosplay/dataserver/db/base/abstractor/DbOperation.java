package com.cosplay.dataserver.db.base.abstractor;
/**
 *数据层传输类
 * */
public abstract class DbOperation {
	/**
	 * 插入一条记录
	 * */
	public abstract boolean insert(Object data);
	/**
	 * 插入多条记录
	 * */
	public abstract boolean insert(Object[]data);
	
	/**
	 * 修改记录 
	 * @param source:需要修改的元数据，data:修改后的数据
	 * */
	public abstract boolean update(Object source,Object data);
	
	/**
	 * 删除一条数据
	 * */
	public abstract boolean delete(Object data);
	/**
	 * 删除多条数据
	 * */
	public abstract boolean delete(Object[] data);
}
