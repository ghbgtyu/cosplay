package com.cosplay.dataserver.util;

import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;

/**
 * json格式类，用于各层之间交互
 * 目前采用mongodb里的DBObject
 * */
public class NJSONObject implements BSONObject {

	@Override
	public Object put(String key, Object v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putAll(BSONObject o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void putAll(Map m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object get(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map toMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object removeField(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsKey(String s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsField(String s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<String> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

}
