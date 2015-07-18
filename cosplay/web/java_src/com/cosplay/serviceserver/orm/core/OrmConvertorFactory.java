package com.cosplay.serviceserver.orm.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.cosplay.bus.log.CosplayLog;
import com.cosplay.serviceserver.base.context.ServiceApplicationContext;
import com.cosplay.serviceserver.base.entity.IEntity;
import com.cosplay.serviceserver.orm.annotation.Collection;
import com.cosplay.serviceserver.orm.annotation.Field;
import com.cosplay.serviceserver.orm.core.impl.DbEntityTemplate;
import com.cosplay.serviceserver.orm.core.interfaces.OrmConvertor;

/**
 * 动态创建ORM转换器
 * */
public class OrmConvertorFactory {
	private final String PACKAGE_NAME = this.getClass().getPackage().getName();
	private final String CLASS_NAME = "EntityConvertorImpl";
	//private final String FILE_NAME = ClassUtil.getClassPath()+PACKAGE_NAME.replace(".", "/")+"/"+CLASS_NAME+".java";
	private final String FILE_NAME = System.getProperty("user.dir")+"/web/"+"java_src/"+PACKAGE_NAME.replace(".", "/")+"/"+CLASS_NAME+".java";
	//获取所有entity类
	private DbEntityTemplate template = new DbEntityTemplate(ServiceApplicationContext.getInstance().ENTITY_PACKAGE_PATH,IEntity.class);
	//所有entity类对象的collect名字
	private Map<Class<?extends Object>,String> collectionMap = new HashMap<Class<?extends Object>,String>();
	
	public Map<Class<? extends Object>, String> getCollectionMap() {
		return collectionMap;
	}

	
	private static OrmConvertorFactory ocFactory = new OrmConvertorFactory();
	public OrmConvertorFactory(){
		init();
		
	}
	private void init(){
		//初始化集合列表
		collectionMapInit();
		//创建orm处理类
		createConvertor();
	}
	private void collectionMapInit(){
		List<Class<?>>classList =template.getClassList();
		for(Class<?>cl:classList){
			collectionMap.put(cl, cl.getAnnotation(Collection.class).name());
		}
	}
	public static OrmConvertorFactory getInstance(){
		return ocFactory;
	}
	private  OrmConvertor convertor;
	/**
	 * 返回orm转换器的实例
	 * */
	public  OrmConvertor getConvertor(){
		if( convertor !=null ){
			return convertor;
		}else{
			return createConvertor();
		}
		
	}
	
	private OrmConvertor createConvertor(){
		//创建java文件
		//File javaFile =createJavaFile();
		createJavaFile();
	
		//编译2
		   //String[] source = { "-d", filePath, new String(CLASS_FILE) };
	       /* System.out.println("javac out:"
	                + com.sun.tools.javac.Main.compile());*/
		//编译
		/*JavaCompiler compiler =  ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
		Iterable units = fileMgr.getJavaFileObjects(javaFile.getAbsolutePath());
		
		CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
		t.call();
		try {
			fileMgr.close();
		} catch (IOException e) {
			CosplayLog.error("orm 关闭 StandardJavaFileManager失败"+e);
		}
		try {
			String classPath = PACKAGE_NAME+"."+CLASS_NAME;
			Class<?> c =ClassUtil.getClassLoader().loadClass(classPath);
			convertor = (OrmConvertor)c.newInstance();
			return convertor;
			
		} catch (ClassNotFoundException e) {
			CosplayLog.error("找不到orm映射转换类"+e);
		} catch (InstantiationException e) {
			CosplayLog.error("newInstance失败"+e);
		} catch (IllegalAccessException e) {
			CosplayLog.error("newInstance失败"+e);
		}finally{
			File file = new File(FILE_NAME);  
		    // 路径为文件且不为空则进行删除  
		    if (file.isFile() && file.exists()) {  
		        //file.delete(); 
		    } 
		}*/
		return new EntityConvertorImpl();
		
		
	}
	/**
	 * 动态创建java类文件
	 * */
	private File createJavaFile(){
		StringBuffer src = new StringBuffer();
		src.append("package ").append(PACKAGE_NAME).append(";")
		.append("import com.cosplay.serviceserver.orm.core.interfaces.OrmConvertor;")
		.append("import com.cosplay.serviceserver.orm.annotation.Field;")
		.append("import com.cosplay.serviceserver.base.entity.IEntity;")
		.append("import java.lang.reflect.Method;")
		.append("import java.util.HashMap;")
		.append("import java.util.Map;")
		.append("public class ").append(CLASS_NAME).append(" implements OrmConvertor ").append(" {")
		.append(createConvertorDbData()).append(createToConvertorSrc())
		.append(createConvertorObjFromDb()).append(createToConvertorObjSrc())
		.append("}");
		File f = new File(FILE_NAME);
		if( !f.exists() ){
			return f;
		}
		FileWriter fw;
		try {
			
			fw = new FileWriter(f);
			fw.write(src.toString());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			CosplayLog.error("orm 创建java文件出错"+e);
		}
		return f;
	}

	private StringBuffer createConvertorDbData(){
		StringBuffer src = new StringBuffer();
		src.append("public Map<String,Object> convertorDbData(Object o) {");
		src.append("if( o==null ){return null;}");
		List<Class<?>>classList =template.getClassList();
		src.append("switch(o.getClass().getName()){");
		for(Class<?>cl:classList){
			src.append("case \"").append(cl.getName()).append("\" : ")
			.append("return ")
			.append("toConvertor(")
			.append("(").append(cl.getName()).append(")o)")
			.append(";");
		}
		src.append("default :return null;}");
		src.append("}");
		return src;
	}
	private StringBuffer createConvertorObjFromDb(){
		StringBuffer src = new StringBuffer();
		src.append("public IEntity convertorObjFromDbData(Map<String,Object> mapData,Class<?extends IEntity>cl) {");
		src.append("if( mapData==null ){return null;}");
		List<Class<?>>classList =template.getClassList();
		src.append("switch(cl.getName()){");
		for(Class<?>cl:classList){
			src.append("case \"").append(cl.getName()).append("\" : ")
			.append("return ")
			.append("toConvertorObj(")
			.append("mapData,new ").append(cl.getName()).append("())")
			.append(";");
		}
		src.append("default :return null;}");
		src.append("}");
		return src;
	}
	private StringBuffer createToConvertorObjSrc(){
		StringBuffer src = new StringBuffer();
		List<Class<?>>classList =template.getClassList();
		for(Class<?>cl:classList){
			src.append("public IEntity toConvertorObj(Map<String,Object> mapData,"+cl.getName()+" entity){");
					Method[] methods = cl.getDeclaredMethods();
					for(Method m :methods){
						Field f  = m.getAnnotation(Field.class);
						if( f!=null ){
							src.append("if(mapData.get(\"").append(f.name()).append("\")").append("!=null){")
							.append("entity.").append(replaceGetToSet(m.getName()))
								.append("(")
									.append("(").append(m.getReturnType().getName()).append(")").append("mapData.get(\"").append(f.name()).append("\")")
								.append(");")
							.append("}");
						}
						Collection c =m.getAnnotation(Collection.class);
						if ( c!=null ){
							src.append("if(mapData.get(\"").append(c.name()).append("\")").append("!=null){")
							.append("entity.").append(replaceGetToSet(m.getName()))
								.append("(")
									.append("(").append(m.getReturnType().getName()).append(")")
									.append("toConvertorObj((Map<String,Object>)").append("mapData.get(\"").append(c.name()).append("\"),")
									.append("new ").append(m.getReturnType().getName()).append("()").append(")")
								.append(");")
							.append("}");
							
						}
						
					}
						src.append("return entity;");
			
			src.append("}");
		}
		return src;
	}
	protected String replaceGetToSet(String name){
		return name.replaceAll("get", "set");
	}
	private StringBuffer createToConvertorSrc(){
		StringBuffer src = new StringBuffer();
		List<Class<?>>classList =template.getClassList();
		for(Class<?>cl:classList){
			src.append("public Map<String,Object> toConvertor("+cl.getName()+" cl){")
					.append("Map<String,Object> resultMap = new HashMap<String,Object>();");
					
					Method[] methods = cl.getDeclaredMethods();
					for(Method m :methods){
						Field f  = m.getAnnotation(Field.class);
						if( f!=null ){
							src.append("if(cl.").append(m.getName()).append("()").append("!=null){")
							.append("resultMap.put(\"").append(f.name()).append("\",cl.").append(m.getName()).append("());")
							.append("}");
							
						}
						Collection c =m.getAnnotation(Collection.class);
						if ( c!=null ){
							src.append("if(cl.").append(m.getName()).append("()").append("!=null){")
							.append("resultMap.put(\"").append(c.name()).append("\",toConvertor(")
							.append("cl.").append(m.getName()).append("()));")
							
							
						.append("}");
						}
						
					}
						src.append("return resultMap;");
			
			src.append("}");
		}
		return src;
	}
	public static void main(String []args) throws InstantiationException, IllegalAccessException {
		OrmConvertorFactory.getInstance().getConvertor();
	/*	EntityClub club = new EntityClub();
		club.setClubId(1);
		club.setClubMessage("message");
		EntityClubMember me = new EntityClubMember();
		me.setMemberId(2);
		me.setMemberJob("job");
		List<EntityClubMember>list = new ArrayList<EntityClubMember>();
		for(int i =0;i<10;i++){
			EntityClubMember cl = new EntityClubMember();
			cl.setMemberId(i);
			cl.setMemberJob("job"+i);
			cl.setUserId(i+1);
			list.add(cl);
		}
		club.setClubMember(me);
	
		System.out.println(OrmConvertorFactory.getInstance().getConvertor().convertorDbData(club));
		EntityClub test = (EntityClub)OrmConvertorFactory.getInstance().getConvertor().convertorObjFromDbData(OrmConvertorFactory.getInstance().getConvertor().convertorDbData(club), EntityClub.class);
		System.out.println(test.getClubMessage());
		System.out.println(test.getClubMember().getMemberJob());*/
	}
}
