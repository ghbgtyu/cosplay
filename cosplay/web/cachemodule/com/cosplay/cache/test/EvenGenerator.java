package com.cosplay.cache.test;

public class EvenGenerator extends IntGenerator {

	private int currentEvenValue = 0;
	@Override
	synchronized public int next() {
		++currentEvenValue; //danger point here
		++currentEvenValue;
		return currentEvenValue;
	}
	public static void main(String []args){
		EvenCheck.test(new EvenGenerator());
	}
}
