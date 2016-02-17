var ERRORCODE={};
var ERROR_CODE = {};

/**根据code 获取code码*/
ERRORCODE.getErrorMsg = function (code){
	
	return ERROR_CODE[code];
}

function register(code,msg){
	ERROR_CODE[code] = msg;
}
function init(){
	register(1001,"用户未登陆");
	register(1002,"不是一个邮箱");
	register(1003,"用户密码格式不正确");
	register(1004,"用户已存在");
	register(1005,"昵称格式不对");
	register(1006,"昵称已存在");
	register(1007,"请拖动验证码");
}

init();