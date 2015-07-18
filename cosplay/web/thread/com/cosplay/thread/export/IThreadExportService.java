package com.cosplay.thread.export;

import java.util.concurrent.ExecutorService;

public interface IThreadExportService {
	/**根据注册的key来取相应的线程service*/
	public ExecutorService getExecutorServiceByKey(String key); 
}
