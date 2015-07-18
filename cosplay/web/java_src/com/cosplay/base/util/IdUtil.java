/**
 * 
 */
package com.cosplay.base.util;


/**
 * @description
 * ID生成器
 */
public class IdUtil {
	
	private static ThreadLocal<Id> IdLocal = new ThreadLocal<Id>(){
		@Override
		protected Id initialValue() {
			return new Id(String.valueOf(Thread.currentThread().getId()));
		}
	};
	
	/**
	 * @param
	 */
	public static String nextString(String prefix){
		return new StringBuffer().append(prefix).append(IdLocal.get().nextString()).toString();
	}

}
