package com.cosplay.cache.container;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.cosplay.base.util.StringUtil;
import com.cosplay.bus.log.CosplayLog;
import com.cosplay.cache.constants.CacheConstants;
import com.cosplay.cache.entity.CacheConfigEntity;
import com.cosplay.cache.entity.CacheEntity;

/**
 * 只读型缓存，高速、但不能修改缓存内容
 * */
public class ReadOnlyCacheContainer <K,V>extends AbsBaseContainer<K,V>  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2806321755459452610L;
	private Map<Object,CacheEntity<V>>cacheContainerMap ;
	/**缓存清理状态，true为正在清理,*/
	private Boolean cleanStatus = false;
	
	private CacheConfigEntity cacheConfigEntity;
	
	private Long clearPeriodic ;
	
	private double clearActive;
	
	private Integer clearCeilingSize;
	
	private CleanCache cacheClearManager = new CleanCache();
	
	private String cacheName = CacheConstants.DEFAULT_CACHE_CONFIG;
	
	synchronized public Integer getClearSize() {
		return clearCeilingSize;
	}
	synchronized public void setClearSize(Integer clearCeilingSize) {
		this.clearCeilingSize = clearCeilingSize;
	}
	protected ReadOnlyCacheContainer(){
		super();
		init();
	}
	public ReadOnlyCacheContainer(String cacheName){
		super(cacheName);
		if( !StringUtil.strIsEmpty(cacheName) ){
			this.cacheName = cacheName;
		}else{
			CosplayLog.error("采用的默认缓存，cacheName可能为空. cacheName:"+cacheName);
		}
		init();
	}
	/**
	 * 初始化
	 * */
	private void init(){
		cacheConfigEntity = super.getCacheConfigEntity();
		cacheContainerMap = new ConcurrentHashMap<Object,CacheEntity<V>>(cacheConfigEntity.getClearCeilingSize());
		clearCeilingSize = cacheConfigEntity.getClearCeilingSize();
		clearPeriodic = (long) (cacheConfigEntity.getClearPeriodic()*60*60*1000);
		clearActive = cacheConfigEntity.getClearActive()/(double)cacheConfigEntity.getCacheActive();
	}
	@Override
	void clean() {
		synchronized(cleanStatus){
			if( !cleanStatus ){
				cleanStatus = true;
				ExecutorService exec = Executors.newSingleThreadExecutor();
				exec.execute(cacheClearManager);
				exec.shutdown();
			}
		}
	}
	/***/
	private class CleanCache implements Runnable{
	
		@Override
		public void run() {
			/**清理模式
			 * 0 ：停止-复制    内存足够时采用 
			 * 1 ：全部清除    内存不足时采用 
			 * 2: 标记删除模式 较快
			 * */
			synchronized(cleanStatus){
				synchronized(cacheContainerMap){
					Integer clearMode = 2;
					if( Runtime.getRuntime().freeMemory() < CacheConstants.CACHE_CLEAN_MAX_MEMORY_SIZE ){
						clearMode = 1;
					}
					switch(clearMode){
						case 0:stopCopyClearStrategy();break;
						case 1:allClearStrategy();break;
						case 2:flagDeleteClearStrategy();break;
						default :flagDeleteClearStrategy();break;
					}
					cleanStatus = false;
				}
			}
		}
		private void allClearStrategy(){
			CosplayLog.cacheInfo("内存不足 "+CacheConstants.CACHE_CLEAN_MAX_MEMORY_SIZE+"清除所有缓存");
			cacheContainerMap.clear();
		}
		/**标记-删除模式*/
		private void flagDeleteClearStrategy(){
			
			int clearSize = 0;
			for(Entry<Object, CacheEntity<V>> entity:cacheContainerMap.entrySet()){
				if( !checkActive(entity.getValue().getActiveTime()) ){
					cacheContainerMap.remove(entity.getKey());
					clearSize++;
				}
			}
			if (clearSize == 0) {
				setClearSize((int)(getClearSize()*1.1));
				CosplayLog.cacheInfo("缓存活跃因子增加为："+getClearSize()+"*********cache:"+cacheName);
			}
		}
		/**停止-复制模式*/
		private void stopCopyClearStrategy(){
			
			Map<Object,CacheEntity<V>>newCacheContainerMap =new ConcurrentHashMap<Object,CacheEntity<V>>();
			Map<Object,CacheEntity<V>>oldCacheContainerMap;
			oldCacheContainerMap =new ConcurrentHashMap<Object,CacheEntity<V>>();
			oldCacheContainerMap.putAll(cacheContainerMap);
			int clearSize = 0;
			for(Entry<Object, CacheEntity<V>> entity:oldCacheContainerMap.entrySet()){
				if( checkActive(entity.getValue().getActiveTime()) ){
					newCacheContainerMap.put(entity.getKey(), entity.getValue());
				}else{
					clearSize++;
				}
			}
			if (clearSize > 0) {
				cacheContainerMap = newCacheContainerMap;
			} else {
				setClearSize((int)(getClearSize()*cacheConfigEntity.getRisePeriodic()));
				CosplayLog.cacheInfo("缓存活跃因子增加为："+getClearSize()+"*********cache:"+cacheName);
			}
		}
		/**活跃为true ，不活跃为false*/
		public boolean checkActive(long activeTime){
			boolean result = false;
			double nowActiveValue = ((activeTime * cacheConfigEntity.getCacheActive())/(System.currentTimeMillis()-clearPeriodic))/cacheConfigEntity.getCacheActive();
			if( nowActiveValue > clearActive ){
				result = true;
			}
			return result;
		}
	}
	/**
	 * 从缓存中读取数据
	 * **/
	@Override
	public V cacheLoad(K key){
		CacheEntity<V> cacheEntity  = null;
		V result =null;
		if( !cleanStatus ){
			cacheEntity = cacheContainerMap.get(key);
			if( cacheEntity == null ){
				return null;
			}
			cacheEntity.setActiveTime(System.currentTimeMillis());
			cacheContainerMap.put(key,cacheEntity);
			result = cacheEntity.getData();
		}
		
		return result;
	}
	/**插入缓存,如果有重复数据则不会插入*/
	@Override
	public void cacheInsert(K key,V value){
		if( cacheContainerMap.containsKey(key) ){
			return;
		}
		CacheEntity<V> cacheEntity = new CacheEntity<V>();
		cacheEntity.setData(value);
		cacheEntity.setActiveTime(System.currentTimeMillis());
		if( !cleanStatus ){
			cacheContainerMap.put(key, cacheEntity);
			//清理程序
			if( cacheContainerMap.size() >= getClearSize() ){
				if( !cleanStatus){
					clean();
				}
			}
		}
		
		
		
	}
	
	@Override
	public boolean equals(Object o) {
		
		return o instanceof ReadOnlyCacheContainer && cacheName.equals(((ReadOnlyCacheContainer)o).cacheName);
	}
	@Override
	public int hashCode() {
		
		return cacheName.hashCode();
	}
	
	
	public int size() {
		// TODO Auto-generated method stub
		return cacheContainerMap.size();
	}
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return cacheContainerMap.isEmpty();
	}
	@Override
	public boolean containsKey(K key) {
		return cacheContainerMap.containsKey(key);
	}
	
	@Override
	public V remove(Object key){
		CacheEntity<V> cacheEntity  = cacheContainerMap.remove(key);
		if(cacheEntity==null){return null;}
		return cacheEntity.getData();
	}
	
	@Override
	public boolean containsValue(V value) {
		return cacheContainerMap.containsValue(value);
	}
	public void clear() {
		clean();
	}
	
	
	
	public  static void main(String []args){
		ReadOnlyCacheContainer<String,String> read = new ReadOnlyCacheContainer<String,String>("register_user_login_name");
		ReadOnlyCacheContainer<String,String> read2 = new ReadOnlyCacheContainer<String,String>("register_user_login_name");
		ReadOnlyCacheContainer<String,String> read3 = new ReadOnlyCacheContainer<String,String>("register_user_login");
		System.out.println(read.equals(null));
		System.out.println(read.equals("dd"));
		System.out.println(read.equals(read2));
		System.out.println(read.equals(read3));
		/*ReadOnlyCacheContainer r = new ReadOnlyCacheContainer("register_user_login_name");
		while(true){
			CacheEntity e =new CacheEntity();
			e.setActiveTime(System.currentTimeMillis());
			e.setData(System.currentTimeMillis());
			r.cacheInsert(System.currentTimeMillis(),e );
		}*/
		//final ReadOnlyCacheContainer readContainer = new ReadOnlyCacheContainer("register_user_login_name");
		//一万人并发
		/*int threadCount = 1000;
		ExecutorService exec = Executors.newFixedThreadPool(threadCount);
		for(int i =0;i<threadCount;i++){
			exec.execute(new Runnable() {
				
				@Override
				public void run() {
					for(int i = 0;i<5000;i++){
						//
						
						//System.out.println(i);
						try {
							TimeUnit.MILLISECONDS.sleep(100);
						readContainer.cacheInsert(this.hashCode()+i, i);
							
								
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					for(int i = 0;i<1000;i++){
						
						String value = (String)readContainer.cacheLoad(this.toString()+i);
						if( value !=null ){
							System.out.println("not null "+i);
							if( !value.equals(this.toString()+i) ){
								System.out.println("这里有问题---value:"+value+"----"+this.toString()+i);
							}else{
								System.out.println("这里没有问题---value:"+value+"----"+this.toString()+i);
							}
						}else{
							//System.out.println("null "+i);
						}
						
						
						//assertNotNull(value);
					}

				}
			});
		
		}
		exec.shutdown();	
		*/
	}
	
	
	
	
}
