package com.cosplay.cosbar.entity;

import org.bson.types.ObjectId;

import com.cosplay.serviceserver.base.entity.IEntity;
import com.cosplay.serviceserver.orm.annotation.Collection;
import com.cosplay.serviceserver.orm.annotation.Field;

/**
 * 闲聊贴吧
 * */
@Collection(name = "chatbar")
public class ChatBarEntity implements IEntity {
	private static final long serialVersionUID = 8892848877012435646L;
	/**主题*/
	private String subject;
	/**楼层ID*/
	private ObjectId storeyId;
	/**楼主ID*/
	private ObjectId masterId;
	/**更新时间*/
	private Long updateTime;
	
	@Field(name = "master_id")
	public ObjectId getMasterId() {
		return masterId;
	}
	@Field(name = "storey_id")
	public ObjectId getStoreyId() {
		return storeyId;
	}
	@Field(name = "subject")
	public String getSubject() {
		return subject;
	}
	@Field(name = "update_time")
	public Long getUpdateTime() {
		return updateTime;
	}
	public void setMasterId(ObjectId masterId) {
		this.masterId = masterId;
	}
	public void setStoreyId(ObjectId storeyId) {
		this.storeyId = storeyId;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	
}
