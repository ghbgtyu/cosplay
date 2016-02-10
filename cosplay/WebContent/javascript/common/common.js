/**
 * 底层公共js
 * 依赖Jquery( 2.1.1
 * 
 * 
 */

document.write(" <script  language=\"javascript\"  src=\"/javascript/common/jquery-2.1.1.min.js\"  > <\/script>");

var COMMON = {};

/**回调函数容器*/
var GLOBAL_CALLBACK_FUNCTION = {};

/**注册回调函数*/
GLOBAL_CALLBACK_FUNCTION.register = function(msg,fun){
	GLOBAL_CALLBACK_FUNCTION.msg=fun;
}
/**调用回调函数*/
GLOBAL_CALLBACK_FUNCTION.callback = function(msg,data){
	GLOBAL_CALLBACK_FUNCTION.msg(data);
}

