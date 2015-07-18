package com.cosplay.cache.test;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.cosplay.cache.container.ReadOnlyCacheContainer;

public class CacheTest {

	@Test
	public void test() {
		/**
		 * 测试用例
		 * 1. 高并发下 读、写 是否有问题
		 * 2. 
		 * 
		 * 
		 * */
		
		final ReadOnlyCacheContainer readContainer = new ReadOnlyCacheContainer("register_user_login_name");
		//一万人并发
		int threadCount = 1000;
		ExecutorService exec = Executors.newFixedThreadPool(threadCount);
		for(int i =0;i<threadCount;i++){
			exec.execute(new Runnable() {
				
				@Override
				public void run() {
					for(int i = 0;i<1000;i++){
						//
						try {
							TimeUnit.MINUTES.sleep(1000);
							readContainer.cacheInsert(i, i);
							readContainer.cacheInsert(this.toString()+i, i);
							
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					for(int i = 0;i<1000;i++){
						
						Object value = readContainer.cacheLoad(i);
						assertNotNull(value);
					}
					
				}
			});
		}
		exec.shutdown();
	}

}
