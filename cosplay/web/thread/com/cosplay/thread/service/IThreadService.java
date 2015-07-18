package com.cosplay.thread.service;

import java.util.concurrent.ExecutorService;

public interface IThreadService {
	
	/**根据注册的key来取相应的线程service*/
	public ExecutorService getExecutorServiceByKey(String key); 
}
