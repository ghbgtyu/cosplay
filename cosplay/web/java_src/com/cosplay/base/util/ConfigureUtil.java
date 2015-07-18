package com.cosplay.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

public class ConfigureUtil {
	
	private static Map<String, Properties> propMap = new HashMap<String, Properties>();
	
	public static int getIntProp(String propFileName, String key){
		return Integer.valueOf(getStringPro(getProperties(propFileName), key));
	}

	public static double getDoubleProp(String propFileName,String key){
		return Double.valueOf(getStringPro(getProperties(propFileName), key));
	}
	
	public static String getProp(String propFileName,String key){
		return getStringPro(getProperties(propFileName), key);
	}

	private static Properties getProperties(String propFileName){
		if(!propMap.containsKey(propFileName)){
			synchronized (propMap) {
				if(!propMap.containsKey(propFileName)){
				    try {
				    	Properties prop =  new Properties();
				    	String file = ClassUtil.getClassPath()+propFileName;
				    	File f = new File(file);
				    	prop.load(new FileInputStream(f));
				    //	prop.load(ClassLoader.getSystemResourceAsStream(propFileName));
				    	propMap.put(propFileName, prop);
				    } catch (Exception e) {
				        throw new RuntimeException("error to load ' " + propFileName + " ',pls check it.",e);
				    }
				
				}
			}
		}
		return propMap.get(propFileName);
	}
	/**
	 * 获取 prop 文件里的value集合
	 * key =  value 
	 * key2 = value2
	 * @return [value1,value2] 
	 * */
	public static String[] getProps(String propFileName,String keyStartWith){
		Properties prop = getProperties(propFileName);
		String []results = new String[0]; 
		Set<Map.Entry<Object, Object>> sets =  prop.entrySet();
		for(Entry<Object, Object> entry:sets){
			String key = (String)entry.getKey();
			if(key.startsWith(keyStartWith)){
				results = (String[])ArrayUtils.add(results, (String)entry.getValue());
			}
		}
		return results;
	}
	/**
	 * 获取prop文件里的值，转换成map形式
	 * key = value
	 * @return Map {key:value}
	 * */
	public static Map<String,String> getPropsMap(String propFileName){
		Properties prop = getProperties(propFileName);
		Map<String,String> result = new HashMap<String,String>();
		Set<Map.Entry<Object, Object>> sets =  prop.entrySet();
		for(Entry<Object, Object> entry:sets){
			result.put((String)entry.getKey(),(String)entry.getValue());
		}
		return result;
	}
	/**
	 * 获取prop文件里的value
	 * key = value
	 * @return value
	 * */
	private static String getStringPro(Properties prop,String key){
		
		if(null==prop) throw new IllegalStateException("' properties ' has not been initialized.");
		
		String str = prop.getProperty(key);
		
		if(null==str) throw new RuntimeException("' " + key + " ' was not configured.");

		return str.trim();
	}

}
