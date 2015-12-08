package com.cosplay.upload.entity;

import java.util.List;

import org.bson.types.ObjectId;

import com.cosplay.serviceserver.base.entity.IEntity;
import com.cosplay.serviceserver.orm.annotation.Collection;

@Collection(name = "photo")
public class PhotoEntity implements IEntity {

	private static final long serialVersionUID = -1531607751322458985L;
	private String fileName;
	private int fileSize;
	private ObjectId oid;
	private List<ObjectId> pid;
	private ObjectId uid;//用户id
	
	
	
	
	
	public List<ObjectId> getPid() {
		return pid;
	}
	public void setPid(List<ObjectId> pid) {
		this.pid = pid;
	}
	public ObjectId getUid() {
		return uid;
	}
	public void setUid(ObjectId uid) {
		this.uid = uid;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public ObjectId getOid() {
		return oid;
	}
	public void setOid(ObjectId oid) {
		this.oid = oid;
	}
	
	
	
	
}
