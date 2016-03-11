var Login ={};
Login.login = function(){
	/*var inputDomLoginName = document.getElementById("userLoginName").value;
	var inputDomCosName = document.getElementById("userCosName").value;
	var inputDomPwd = document.getElementById("userPassWord").value;*/
	//发送ajax请求验证
	/*jQuery.ajax({
		   type: "POST",
		   url: "/register?userCosName="+inputDomCosName+"&userLoginName="+inputDomLoginName+"&userPassWord="+inputDomPwd,
		   success: function(msg){
		     if( msg.result == false ){
		    	 alert(ERRORCODE.getErrorMsg(msg.code));
		     }else{
		    	alert("ok");
		     }
		   },
		   async: true,
		   complete: function (XHR, TS) { XHR = null }
		});*/
	

    jQuery.ajax({
		cache : true,
		type : "POST",
		url : "/login",
		data : $('#login').serialize(),// 你的formid
		success : function(msg) {
			if (msg.result == false) {
				alert(ERRORCODE.getErrorMsg(msg.code));
			} else {
				alert("ok");
			}
		},
		async : true,
		complete : function(XHR, TS) {
			XHR = null
		}
	});
	
}