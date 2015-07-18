/**
 * 
 */
package com.cosplay.bus.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class CosplayLog {
	
	private static final String ERROR_LOGGER = "error_logger";
	private static final String ERROR_CACHE_LOGGER = "error_cache_logger";
	private static final String _1MIN_LOGGER = "_1min_logger";
	private static final String _60MIN_LOGGER = "_60min_logger";
	private static final String _CHAT_LOG = "chatLog_logger";
	private static final String STATUS_LOGGER = "server_status_logger";
	private static final String CONFIG_LOGGER = "config_error_logger";
	private static final String FRAME_LOGGER = "frame_logger";
	
	private static Logger CACHELOG = LogManager.getLogger(ERROR_CACHE_LOGGER);
	private static Logger LOG = LogManager.getLogger(ERROR_LOGGER);
	private static Logger _1MINLOG = LogManager.getLogger(_1MIN_LOGGER);
	private static Logger _60MINLOG = LogManager.getLogger(_60MIN_LOGGER);
	private static Logger STATUSLOG = LogManager.getLogger(STATUS_LOGGER);
	private static Logger CONFIGLOGGER = LogManager.getLogger(CONFIG_LOGGER);
	private static Logger FRAMELOGGER = LogManager.getLogger(FRAME_LOGGER);
	private static Logger CHATLOG = LogManager.getLogger(_CHAT_LOG);
	private static Logger DEVLOG = LogManager.getLogger("_dev_logger");
	
	/**
	 * 错误信息打印
	 * @param
	 */
	public static void error(String message,Exception e){
		LOG.error(message, e);
	}
	public static void debug(String message,Exception e){
		LOG.debug(message, e);
	}
	
	/**
	 * debug
	 */
	public static void debug(String message, Object... args){
		LOG.debug(message, args);
	}
	
	

	/**
	 * 错误信息打印
	 */
	public static void error(String message, Object... args){
		LOG.error(message, args);
	}
	/**
	 * 调试信息打印
	 */
	public static void info(String message, Object... args){
		LOG.info(message, args);
	}

	/**
	 * 1分钟解析间隔信息打印
	 * @param
	 */
	public static void _1MinuteLog(String message){
		_1MINLOG.error(message);
	}
	/**
	 * 1分钟解析间隔信息打印(info级别)
	 * @param
	 */
	public static void _1MinuteLogInfo(String message){
		_1MINLOG.info(message);
	}
	
	/**
	 * 1分钟解析间隔信息打印
	 * @param
	 */
	public static void _60MinuteLog(String message){
		_60MINLOG.error(message);
	}
	/**
	 * 1分钟解析间隔信息打印(info级别)
	 * @param
	 */
	public static void _60MinuteLogInfo(String message){
		_60MINLOG.info(message);
	}
	
	
	/**
	 * 服务器状态日志
	 * @param message
	 */
	public static void statusLog(String message, Object... args){
		STATUSLOG.error(message, args);
	}
	/**
	 * 服务器配置文件错误日志
	 * @param message
	 */
	public static void configError(String message, Object... args){
		CONFIGLOGGER.error(message, args);
	}
	/**
	 * 服务器配置文件错误日志
	 * @param message
	 */
	public static void configInfo(String message, Object... args){
		CONFIGLOGGER.info(message, args);
	}
	/**
	 * 服务器配置文件错误日志
	 * @param message
	 */
	public static void configError(String message, Exception e){
		CONFIGLOGGER.error(message, e);
	}
	
	/** 框架错误信息 */
	public static void frameError(String msg, Object... args){
		FRAMELOGGER.error(msg, args);
	}

	/** 框架错误信息 */
	public static void frameInfo(String msg, Object... args){
		FRAMELOGGER.info(msg, args);
	}
	public static void cacheInfo(String msg,Object... args){
		CACHELOG.error(msg,args);
	}
	/**
	 * 取时间戳到秒
	 */
	public static String getTimeStamp(){
		return String.valueOf(System.currentTimeMillis()).substring(0,10);
	}
	
	public static boolean isNumber(String userId){
		return userId.matches("[0-9]*");
	}
	/**
	 * 聊天记录
	 * @param chatmessage
	 */
	public static void chatLog(String chatmessage){
		CHATLOG.error(chatmessage);
	}
	/**
	 * 聊天记录
	 * @param chatmessage
	 */
	public static void chatLog(String chatMsg, Object... parameters){
		CHATLOG.error(chatMsg, parameters);
	}
	/**
	 * 开发人员日志(info级别，用于BUG查找)
	 * @param msg
	 * @param parameters
	 */
	public static void devInfo(String msg, Object... parameters){
		DEVLOG.info(msg, parameters);
	}
	
	public static void main(String []args){
		CosplayLog.error("error test");
	}
}
