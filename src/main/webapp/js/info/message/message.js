/**
 * 	短信平台JS层
 *  @auto liyuna
 *  @date 2012-1-31
 */
var ctxPath;
var messageWin;
var messageForm;
$(document).ready(function() {
	ctxPath = $('#ctxPath').val();
	$('input[type=checkbox]').click(function(e) {
		e.stopPropagation();
	});
	$("input[type=checkbox]").each(function() {
		$(this).attr("checked", false);
	});
	//设置全选
	/**
	$("#check_all").click(function() {
		$("input[name='check_li']").each(function() {
			$(this).attr("checked", !this.checked);
		});
	});
	*/
	$("#check_all").click(function(){
	    $('input[name="check_li"]').attr("checked", this.checked);
	});
	var $subBox = $("input[name='check_li']");
	$subBox.click(function(){
	    $("#check_all").attr("checked", $subBox.length == $("input[name='check_li']:checked").length ? true : false);
	});
});

var setting = {
		check: {
			enable: true,
			chkboxType: {"Y" : "s", "N" : "ps" }
		},
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: beforeClick,
			onCheck: onCheck
		}
	};


	function beforeClick(treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		return false;
	}
	
	function onCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getCheckedNodes(true),
		v = "";
		s= "";
		for (var i=0, l=nodes.length; i<l; i++) {
			if(nodes[i].remark=="1"){
				v += nodes[i].name + ",";
				s += nodes[i].id + ",";
			}
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		if (s.length > 0 ) s = s.substring(0, s.length-1);
		var acceptNameObj = $("#acceptName");
		acceptNameObj.attr("value", v);
		var acceptObj=$("#acceptId");
		acceptObj.attr("value",s);
	}

/*	function showMenu() {
		var acceptNameObj = $("#acceptName");
		var acceptNameOffset = $("#acceptName").offset();
		$("#menuContent").css({left:acceptNameOffset.left + "px", top:acceptNameOffset.top + acceptNameObj.outerHeight() + "px"}).slideDown("fast");
		$("body").bind("mousedown", onBodyDown);
	}*/
	/*function hideMenu() {
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}*/
	/*function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "acceptName" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
			hideMenu();
		}
	}*/


//删除单条记录
function delBasMes(id) {
	var url = ctxPath + "/rest/basmessage/delete/" + id;
	if (confirm("确定要删除该条记录？")) {
		$.get(url, null, function(data) {
			alert(data.message);
			if (data.status == "success") {
				location.href = ctxPath + "/rest/basmessage/list?pageIndex="+$("#pageIndex").val();
			}
		});
	}
}

function checkThis(tr) {
	if ($(tr).children().first().children().attr("checked"))
		$(tr).children().first().children().attr("checked", false);
	else
		$(tr).children().first().children().attr("checked", true);
}

//批量删除
function delMulti() {
	var arrChecked = $("input[name='check_li']:checked");
	if (arrChecked.length == 0) {
		alert('请选择要删除的记录！');
		return;
	}
	var records = new Array();
	$(arrChecked).each(function() {
		records.push(this.value);
	});
	var url = ctxPath + "/rest/basmessage/delete/multiple";
	if (confirm("确定要删除选中的记录么？")) {
		$.get(url, "records=" + records, function(data) {
			if(data.status=='success'){
				alert(data.message);
				location.href = ctxPath + "/rest/basmessage/list?pageIndex="+$("#pageIndex").val();
			}
			
		});
	}
}

function stopBubble(e) {
	if (e.stopPropagation) {
		e.stopPropagation();
		e.preventDefault();
	} else {
		e.returnValue = false;
		e.cancelBubble = true;
	}
}

//新添
function newBasMes(){
	location.href = ctxPath + "/rest/basmessage/post/pre";

}

//修改
function editBasMes(id){
	location.href = ctxPath + "/rest/basmessage/"+id+"/put/pre"
}

//==保存信息
function saveBasMes(){
	var messageForm=$("#messageForm");
	messageForm.form('submit',{
		onSubmit:function(){
		if (messageForm.form('validate')){
			$("#messageForm").submit();
			

			}
			return false;
		}
	});
}

//==关闭用户窗口
function closeWindow(){
	messageWin.window('close');
};


//==窗体创建并打开
//function openForm(formName,winURL,title,btnURL,btnMethod){
//	$.get(winURL,null,function(resData){
//		//==加载窗口
//		$('<div id="easyWindow" style="width:500px;height:300px;"></div>').appendTo('body');
//		$('#easyWindow').append(resData);
//
//		//==创建窗体并打开
//		messageWin = $('#easyWindow').createWindow(title);
//		messageForm = messageWin.find('form');
//		messageForm.url = btnURL;
//		messageForm.method = btnMethod;
//		messageWin.window('open');
//		
//		//==组件状态
//		if ('show'==formName) {//查看
//			socialForm.find('input').attr('disabled',true);//组件disable
//			socialForm.find('select').attr('disabled',true);//组件disable
//			$('[class="formButton"]').remove();//去掉确定和取消按钮
//		}
//		
//		if ('new'==formName || 'edit'==formName) {//查看
//			var zNodes=jQuery.parseJSON($("#groupJson").val());
//			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
//		}
//	});
//}; 
 
  //取消
  function cancel(){
  	location.href = ctxPath + "/rest/basmessage/list";
  }




