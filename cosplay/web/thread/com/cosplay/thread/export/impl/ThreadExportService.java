package com.cosplay.thread.export.impl;

import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cosplay.thread.export.IThreadExportService;
import com.cosplay.thread.service.IThreadService;

@Component
public class ThreadExportService implements IThreadExportService {
	
	@Autowired
	private IThreadService threadService;

	@Override
	public ExecutorService getExecutorServiceByKey(String key) {
		return threadService.getExecutorServiceByKey(key);
	}

}
