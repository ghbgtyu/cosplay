package com.cosplay.check.model;

import org.bson.types.ObjectId;

public class LoginCheckResult extends AbsCheckResult {
	
	private ObjectId id;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
	
	
}
