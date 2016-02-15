package com.cosplay.check.handler.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cosplay.bus.code.ErrorCode;
import com.cosplay.check.constants.CheckConstants;
import com.cosplay.check.handler.AbsCheckHandler;
import com.cosplay.check.model.NormalCheckResult;

/**检验是否是email*/
public class EmailCheckHandler extends AbsCheckHandler {
	
	private String email;
    private NormalCheckResult result ;

    public EmailCheckHandler(NormalCheckResult result,String email) {
    	this.result = result;
    	this.email = email;
    }
    
	@Override
	public boolean check() {
		Pattern p = Pattern.compile(CheckConstants.MATCH_USER_EMAIL);
		Matcher m = p.matcher(email);
		boolean flag = m.matches();
		if(flag){
			this.result.setSuccess(true);
		}else{
			this.result.setErrorCode(ErrorCode.ERROR_1002);
			this.result.setSuccess(false);
		}
		return flag;
	}

}
