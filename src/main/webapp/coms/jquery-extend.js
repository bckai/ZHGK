//==默认回调函数
var defaultCallBack = function(resData){
	if(resData.status == "success"){
		window.location.reload();
		top.msgTipSuccess(resData.message);
	} else {
		top.msgTipFail(resData.message);
	}
};

(function($) {
	$.extend({
		put: function(url, data, callback, type) {
	        if ( $.isFunction( data ) ) {
				type = type || callback;
				callback = data;
				data = null;
			}
			return $.ajax({
				type: "PUT",
				url: url,
				data: data,
				success: callback,
				dataType: type
			});
	    },
	    //delete是关键字不能直接使用
	    delete_: function(url, data, callback, type) {
	       if ( $.isFunction( data ) ) {
				type = type || callback;
				callback = data;
				data = null;
			}
			return $.ajax({
				type: "DELETE",
				url: url,
				data: data,
				success: callback,
				dataType: type
			});
	    },
	    getJSON:function(url, data, callback) {
	    	//==如果回调函数为空的话，创建默认的回调函数
	    	if(callback == null){
	    		callback = defaultCallBack;
	    	}
	    	//==执行提交
	    	return $.get(url, data, callback, 'json');
	    },
	    postJSON:function(url, data, callback) {
	    	//==如果回调函数为空的话，创建默认的回调函数
	    	if(callback == null){
	    		callback = defaultCallBack;
	    	}
	    	//==执行提交
	    	return $.post(url, data, callback, 'json');
	    },
	    putJSON: function (url, data, callback) {
	    	//==虚拟PUT方法
	    	if(data == null) { data = "_method=put" }
	    	else { data = "_method=put&" + data; };

	    	//==执行提交
	    	return $.postJSON(url, data, callback);
	    },
	    deleteJSON: function(url, data, callback) {
	    	//==如果回调函数为空的话，创建默认的回调函数
	    	if(callback == null){
	    		callback = defaultCallBack;
	    	}

	    	//==执行提交
	    	return $.delete_(url, data, callback, "json");
	    },
	    ajaxPost:function(url, data, callback) {
	    	//==如果回调函数为空的话，创建默认的回调函数
	    	if(callback == null){
	    		callback = defaultCallBack;
	    	}

	    	//==执行提交
	    	return $.ajax({
				type: "POST",
				url: url,
				data: data,
				success: callback,
				contentType:"application/json",
				dataType: "json"
			});
		},    
	    ajaxPut: function (url, data, callback) {
	    	//==如果回调函数为空的话，创建默认的回调函数
	    	if(callback == null){
	    		callback = defaultCallBack;
	    	}
	    	
	    	//==执行提交
	    	return $.ajax({
				type: "PUT",
				url: url,
				data: data,
				success: callback,
				contentType:"application/json",
				dataType: "json"
			});
	    },
	    getGuid:function(){
	    	var guid = "";
	    	for (var i = 1; i <= 32; i++){
		    	var n = Math.floor(Math.random()*16.0).toString(16);
		    	guid += n;
		    	if((i==8)||(i==12)||(i==16)||(i==20))
		    		guid += "-";
	    	}
	    	return guid; 
	    }
	});
	
})(jQuery);
