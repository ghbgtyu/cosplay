package com.cosplay.command.template;



import com.cosplay.command.annotation.CommandAction;
import com.cosplay.serviceserver.orm.core.support.AnnotationClassTemplate;

public class CommandActionTemplate extends AnnotationClassTemplate {
	

	public CommandActionTemplate(String packageName, Class<?> superClass) {
		super(packageName, CommandAction.class);
	}
	
	
	

}
