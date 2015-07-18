package com.cosplay.cache.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenCheck implements Runnable {

	private IntGenerator generator;
	private int id;
	public EvenCheck(IntGenerator g,int ident){
		generator = g;
		id = ident;
	}
	@Override
	public void run() {

			while(!generator.isCanceled()){
				int val = generator.next();
				if( val % 2 !=0 ){
					System.out.println(val+" not even");
					generator.cancel();
				}
			}
	}
	public static void test(IntGenerator gp,int count){
		System.out.println("Press Control to exit");
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0;i<count;i++){
			exec.execute(new EvenCheck(gp, i));
		}
		exec.shutdown();
	}
	public static void test(IntGenerator gp){
		test(gp,10);
	}
}
