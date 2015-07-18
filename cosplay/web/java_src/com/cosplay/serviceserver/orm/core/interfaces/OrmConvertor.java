package com.cosplay.serviceserver.orm.core.interfaces;

import java.util.Map;

import com.cosplay.serviceserver.base.entity.IEntity;

public interface OrmConvertor {
	Map<String,Object> convertorDbData(Object o);
	IEntity convertorObjFromDbData(Map<String,Object> dataMap,Class<?extends IEntity>cl);
}
