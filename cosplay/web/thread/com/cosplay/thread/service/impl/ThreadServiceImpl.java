package com.cosplay.thread.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Component;

import com.cosplay.bus.log.CosplayLog;
import com.cosplay.thread.config.ThreadConfig;
import com.cosplay.thread.entity.ThreadConfigEntity;
import com.cosplay.thread.service.IThreadService;

@Component
public class ThreadServiceImpl implements IThreadService {
	
	private Map<String,ExecutorService> threadServiceMap = new HashMap<String,ExecutorService>();
	

	
	public ThreadServiceImpl(){
		init();
	}
	private void init(){
		//创建线程池
		for(Entry<String, ThreadConfigEntity>entry:ThreadConfig.getThreadProList().entrySet()){
			ExecutorService executor = Executors.newFixedThreadPool(entry.getValue().getMaxSize());
			if( !threadServiceMap.containsKey(entry.getKey()) ){
				threadServiceMap.put(entry.getKey(),executor);
			}
		}
		
	}
	@Override
	public ExecutorService getExecutorServiceByKey(String key) {
		if( !threadServiceMap.containsKey(key) ){
			CosplayLog.error("线程配置中没有相应的key ，这边默认给一个sing executor");
			return Executors.newSingleThreadExecutor();
		}
		
		return threadServiceMap.get(key);
	}
	
}
