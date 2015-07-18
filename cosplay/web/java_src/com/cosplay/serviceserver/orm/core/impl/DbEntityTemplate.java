package com.cosplay.serviceserver.orm.core.impl;

import com.cosplay.serviceserver.orm.annotation.Collection;
import com.cosplay.serviceserver.orm.core.support.AnnotationClassTemplate;

public class DbEntityTemplate extends AnnotationClassTemplate{

	public DbEntityTemplate(String packageName, Class<?> superClass) {
		super(packageName, Collection.class);
	}

	

}
