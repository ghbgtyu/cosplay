package com.cosplay.upload.moduleinit;

import org.springframework.stereotype.Component;

import com.cosplay.serviceserver.eventservice.model.IEventHandler;
import com.cosplay.serviceserver.init.abstracts.AbsModuleInit;
@Component
public class UploadModuleInit extends AbsModuleInit {
	@Override
	protected IEventHandler[] getEventHandlers() {
		return super.getEventHandlers();
	}
}
