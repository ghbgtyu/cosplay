package com.cosplay.thread;

/**线程模块 ，配置key*/
public class ThreadKey {
	private static ThreadKey threadKey = new ThreadKey();
	/**websocket模块*/
	public  String WEBSOCKET_KEY="websocket";
	
	public static ThreadKey getInstance(){
		return threadKey;
	}
}
