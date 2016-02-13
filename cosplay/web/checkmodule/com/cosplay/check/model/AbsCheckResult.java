package com.cosplay.check.model;
/**检验返回值*/
public abstract class AbsCheckResult {
	private boolean isSuccess;
	
	private int errorCode;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	
}
