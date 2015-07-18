package com.cosplay.serviceserver.orm.core.support;

import java.lang.annotation.Annotation;

import com.cosplay.serviceserver.orm.annotation.Collection;

/**
 * 用于获取注解类的模板类
 *
 * @author
 * @since 2.3
 */
public abstract class AnnotationClassTemplate extends ClassTemplate {

    protected final Class<? extends Annotation> annotationClass;

    protected AnnotationClassTemplate(String packageName, Class<? extends Annotation> annotationClass) {
        super(packageName);
        this.annotationClass = annotationClass;
    }
    @Override
	public boolean checkAddClass(Class<?> cls) {
    	Annotation collection = cls.getAnnotation(annotationClass);
		if( collection!=null ){
			return true;
		}else{
			return false;
		}
	}
}
