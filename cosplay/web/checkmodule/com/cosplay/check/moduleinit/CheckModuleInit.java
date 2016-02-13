package com.cosplay.check.moduleinit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cosplay.base.util.SpringContextUtil;
import com.cosplay.check.service.ICheckService;
import com.cosplay.serviceserver.eventservice.model.IEventHandler;
import com.cosplay.serviceserver.init.abstracts.AbsModuleInit;
@Component
public class CheckModuleInit extends AbsModuleInit {
	
	@Autowired
	private SpringContextUtil s;
	
	@Override
	protected IEventHandler[] getEventHandlers() {
		return super.getEventHandlers();
	}

	@Override
	public void otherInit() {
		
	}
	
	
}
