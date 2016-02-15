package com.cosplay.check.handler.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cosplay.bus.code.ErrorCode;
import com.cosplay.check.constants.CheckConstants;
import com.cosplay.check.handler.AbsCheckHandler;
import com.cosplay.check.model.NormalCheckResult;
/**检验密码*/
public class PwdCheckHandler extends AbsCheckHandler {
	
	private String pwd;
    private NormalCheckResult result ;

    public PwdCheckHandler(NormalCheckResult result,String pwd) {
    	this.result = result;
    	this.pwd = pwd;
    }
    
	@Override
	public boolean check() {
		Pattern p = Pattern.compile(CheckConstants.MATCH_PASS_WORLD);
		Matcher m = p.matcher(pwd);
		boolean flag = m.matches();
		if(flag){
			this.result.setSuccess(true);
		}else{
			this.result.setErrorCode(ErrorCode.ERROR_1003);
			this.result.setSuccess(false);
		}
		return flag;
	}

}
