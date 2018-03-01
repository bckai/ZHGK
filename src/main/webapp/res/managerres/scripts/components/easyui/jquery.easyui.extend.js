/**
 * jquery EasyUi的扩展方法
 *
 * @author ibm 添加验证规则：hyShuai
 */

(function($) {
	$.fn.extend({
		//==查询条件用的Panel,关键解决Collapse问题
		findPanel:function(){
			$(this).panel({
				onBeforeCollapse : function(){
					this.panelHeight = $(this).parent().height();
					this.bodyHeight = $(this).height();
				},
				onCollapse : function(){
					$(this).parent().parent().css("height",this.panelHeight-this.bodyHeight);//设置容器高度
					$('div[region="center"]').panel('resize', {top:this.panelHeight-this.bodyHeight+10,height:$('div[region="center"]').parent().height()+this.bodyHeight});
				},
				onBeforeExpand: function(){
					$(this).parent().parent().css("height",this.panelHeight);//恢复容器高度
					$('div[region="center"]').panel('resize', {top:this.panelHeight+10,height:$('div[region="center"]').parent().height()-this.bodyHeight});
				}
			})
		},
		createWindow:function(title){//创建EasyUI的窗口对象
			//==窗口创建
			$('.easyui-linkbutton').linkbutton();
			$('.easyui-validatebox').validatebox();
			return $(this).window({
				iconCls:'icon-window',
				closed:true,
				modal:true,
				title:'&nbsp'+title,
				top:'40px',
				minimizable:false,
				onClose:function(){$(this).remove();}
			});
		},
		//显示遮罩 使用了dataGrade的loading(适合用户操作等待界面)
	   loading: function(loadMsg){
			return $(this).each(function(){
				var wrap = $(this).css("z-index","-1");
				if(loadMsg) {
				$("<div class=\"datagrid-mask\"></div>").css({display:"block",width:wrap.width(),height:wrap.height()}).appendTo(wrap).css("opacity","0.3");
				$("<div class=\"datagrid-mask-msg\"></div>").html(loadMsg).appendTo(wrap).css({display:"block",left:(wrap.width()-$("div.datagrid-mask-msg",wrap).outerWidth())/2,top:(wrap.height()-$("div.datagrid-mask-msg",wrap).outerHeight())/2});
				}
			});
		},
		//隐藏遮罩
		loaded: function(){
			return $(this).each(function(){
				$(this).css("z-index","0");
				$(this).find("div.datagrid-mask-msg").remove();
				$(this).find("div.datagrid-mask").remove();
			});
		}
	});
	//Jquery easyUI validatebox extends(
	$.extend($.fn.validatebox.defaults.rules,{
       mobile:{  
           validator:function(value,param){  
               return /^(13[0-9]|15[0-9]|18[0|5|6|7|8|9])\d{8}$/.test(value);
           },  
           message:'请输入正确的11位手机号码.格式:13015385461'
       }, 
       ctMobile:{  
           validator:function(value,param){  
               return /^(13[3]|15[3]|18[0|9])\d{8}$/.test(value);
           },  
           message:'请输入正确的11位中国电信手机号码.格式:13315385461'
       },
       postcode:{
	       validator:function(value,param){
	           return /^\d{6}$/.test(value);
	       },  
	       message:'请输入正确的6位邮政编码' 
       },
       phone:{  
           validator:function(value,param){  
               return  /^([0-9]|[-])+$/.test(value);
           },  
           message:'请输入正确的电话格式.格式:13315385461、0371-67471531'
       },
	   tel:{
	   	 	validator:function(value,param){
               return /^(((\+86)|(86)|(086))\-?)?((0[1-9]\d{1,2}[-]?)|(\(0[1-9]\d{1,2}\)?))?([1-9]\d{6,7})(\-\d{1,4})?$/.test(value);
          	 },  
          	 message:'请输入正确的电话号码。格式：+86、86、086、020、020-、(020)或者正确传真号码。'
	   },
	   //经度验证规则、针对河南省区域
	   longitude:{
	   		 validator:function(value,param){
	           return /^([1][1][0-7][.]\d{6})$/.test(value);
	       }, 
	       message:'请输入正确经度格式，如:113.7601126。' 
	   },
	   //纬度验证规则、针对河南省区域
	   latitude:{
	   		validator:function(value,param){
	           return /^([3][1-6][.]\d{6})$/.test(value);
	       },
	       message:'请输入正确纬度格式，如:34.699785。' 
	   },
	   number:{  
           validator:function(value,param){  
               return  /^[0-9]*$/.test(value);
           },  
           message:'请输入正确的数字格式.格式:1、12'
       }
	});
})(jQuery);


