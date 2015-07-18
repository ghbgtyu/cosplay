/**
 * 
 */
package com.cosplay.base.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @description
 * @author ShiJie Chi
 * @created 2011-12-16上午11:32:21
 */
public class Id{
	
	private String prefix;
	
	public Id(String prefix) {
		this.prefix = prefix;
	}
	
	private AtomicLong increase = new AtomicLong();
	
	public String nextString(){
		return new StringBuffer().append(prefix).append(Long.toHexString(increase.getAndIncrement())).toString();
	}
	
	public long nextLong(){
		return increase.getAndIncrement();
	}
	
}
