var BODYWIDTH = document.documentElement.clientWidth |  document.documentElement.offsetWidth;
var BODYHEIGHT = document.documentElement.clientHeight | document.documentElement.offsetHeight;

var FRAMEHEIGHT = BODYHEIGHT - 50;
var FRAMEWIDTH = BODYWIDTH;

var SERVER = "http://192.168.1.23:8082/rb/rest/";
var IMGURL = "http://192.168.1.23:8082/rb/";

var AJAXMETHOD = "get";
var AJAXJSON = "jsonp";

$(document).ready(function() {
	//alert(FRAMEWIDTH);
	$(".rb_mainframe_middle").css("height",FRAMEHEIGHT-20+"px");
	
	//去除页面缓存
	$("head").append("<META HTTP-EQUIV='pragma' CONTENT='no-cache'> <META HTTP-EQUIV='Cache-Control' CONTENT='no-store, must-revalidate'> <META HTTP-EQUIV='expires' CONTENT='0'>");
});

//命名空间
if(typeof RB == "undefined"){
	var RB = {};	
}

//Toast组件
RB.Toast = function (String){
	var s = "<div class='rb_toast'>"+String+"</div>";
	if ($(".rb_toast").length > 0){
		$(".rb_toast").html(String);
		$(".rb_toast").slideDown(200).delay(2000).slideUp(200);
	}else{		
		$("body").append(s);
		$(".rb_toast").slideDown(200).delay(2000).slideUp(200);
	}
}

//Alert组件
RB.Alert = function (title,String,fun){
	var str = "<div class='rb_alert_mask'><div class='rb_alert'><div>"+title+"</div><div>"+String+"</div><div><div id='alert_btn_ok'>确定</div><div id='alert_btn_cancel'>取消</div><div></div></div></div></div>";
	$("body").append(str);
	$(".rb_alert_mask").css("top", window.pageYOffset+"px");
	
	$('.rb_alert_mask').on('touchmove',function(e) {e.preventDefault();});
	$('#alert_btn_cancel').click(function(){
		$('.rb_alert_mask').remove();	
	});
	
	$('#alert_btn_ok').click(function(){
		eval(fun);
		$('.rb_alert_mask').remove();
	});
}

//AlertInfo组件
RB.AlertInfo = function (title,String,fun){
	var str = "<div class='rb_alert_mask'><div class='rb_alert_info'><div>"+title+"</div><div>"+String+"</div><div><div id='alert_btn_ok'>确定</div><div id='alert_btn_cancel'>取消</div><div></div></div></div></div>";
	$("body").append(str);
	$(".rb_alert_mask").css("top", window.pageYOffset+"px");
	
	$('.rb_alert_mask').on('touchmove',function(e) {e.preventDefault();});
	
	$('#alert_btn_ok').click(function(){
		$('.rb_alert_mask').remove();
		eval(fun);
	});
}

//localStorage
var lstorage = window.localStorage;
RB.Lstrorage = function (boolean,name,str){
	var _str;
	if (boolean){
		lstorage.setItem(name,str);	
		return false;	
	}else{
		_str = lstorage.getItem(name);	
	}
	return _str;
}

//sessionStorage
var sstorage = window.sessionStorage;
RB.Sstrorage = function (boolean,name,str){
	var _str;
	if (boolean){
		sstorage.setItem(name,str);	
		return false;	
	}else{
		_str = sstorage.getItem(name);	
	}
	return _str;
}

//日期格式化
Date.prototype.DateFormat = function(format) { 

	var o = { 
		"M+" : this.getMonth() + 1, 
		"d+" : this.getDate(), 
		"h+" : this.getHours(), 
		"m+" : this.getMinutes(), 
		"s+" : this.getSeconds(), 
		"q+" : Math.floor((this.getMonth() + 3) / 3), 
		"S" : this.getMilliseconds() 
	} 
	 
	if (/(y+)/.test(format)) 
	{ 
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length)); 
	} 
	 
	for (var k in o) 
	{ 
		if (new RegExp("(" + k + ")").test(format)) 
		{ 
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length)); 
		} 
	} 
	return format; 
} 
