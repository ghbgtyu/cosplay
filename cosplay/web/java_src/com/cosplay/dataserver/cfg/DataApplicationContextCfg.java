package com.cosplay.dataserver.cfg;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.cosplay.bus.log.CosplayLog;
import com.cosplay.dataserver.bean.MongoDbBean;
/**
 * 从xml配置文件读取信息
 * */
public class DataApplicationContextCfg {
	private final String contextXmlCfgPath = DataApplicationContextCfg.class.getResource("DataApplicationContext.xml").getFile();
	private List<MongoDbBean> mongoDbBeanList = new ArrayList<MongoDbBean>();

	private static DataApplicationContextCfg dataApplicationContextCfg = new DataApplicationContextCfg();
	public static DataApplicationContextCfg getInstance(){
		return dataApplicationContextCfg;
	}
	private DataApplicationContextCfg(){
		init();
	}

	private void init(){
		try {
			Document document = read(contextXmlCfgPath);
			Element root = getRootElement(document);
			for ( Iterator<?> i = root.elementIterator("mongodb"); i.hasNext();) {
			    Element mongodbElement = (Element) i.next();
				MongoDbBean mongodb = new MongoDbBean();
				mongodb.setLocalAddress(mongodbElement.elementText("localAddress"));
				mongodb.setPassWord(mongodbElement.elementText("passWord"));
				mongodb.setPort(Integer.valueOf(mongodbElement.elementText("port")));
				mongodb.setUserName(mongodbElement.elementText("userName"));
				mongodb.setDatabase(mongodbElement.elementText("database"));
			    mongoDbBeanList.add(mongodb);
			}
		} catch (MalformedURLException e) {
			CosplayLog.error("MalformedURLException:"+e);
		} catch (DocumentException e) {
			CosplayLog.error("DocumentException:"+e);
		}
		
	}
	private  Document read(String filePath) throws MalformedURLException, DocumentException {
	    SAXReader reader = new SAXReader();
	    Document document = reader.read(new File(filePath));
	    return document;
	}
	private  Element getRootElement(Document doc){
	    return doc.getRootElement();
	}
	public List<MongoDbBean> getMongoDbBeanList() {
		return mongoDbBeanList;
	}
}
