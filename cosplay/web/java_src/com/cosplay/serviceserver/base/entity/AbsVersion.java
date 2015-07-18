package com.cosplay.serviceserver.base.entity;

public abstract class AbsVersion implements IVersion{
	private long version = System.currentTimeMillis();
	
	@Override
	public String getVersion() {
		return String.valueOf(version);
	}

	@Override
	public void updateVersion() {
		this.version = System.currentTimeMillis();
	}
}
