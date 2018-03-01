  /**
 * 	通讯录ＪＳ层
 *  @auto liyuna
 *  @date 2012-1-31
 */
var ctxPath;
var bookWin;
var bookForm;
$(document).ready(function() {
	ctxPath = $('#ctxPath').val();
	
	$('input[type=checkbox]').click(function(e) {
		e.stopPropagation();
	});
	$("input[type=checkbox]").each(function() {
		$(this).attr("checked", false);
	});
//	// 设置全选
//		$("#check_all").click(function() {
//			$("input[name='check_li']").each(function() {
//				$(this).attr("checked", !this.checked);
//			});
//		});
	$("#bookForm").validate({
		rules : {
			name : {
				required : true,
				minlength : 2,
				maxlength : 20
			},tel : {
				required : false,
				minlength : 4,
				maxlength : 100
			},mail : {
				required : false,
				mailPrefix:true
			},deptVal : {
				required : true,
				minlength : 1
			},tel1 : {
				required : false,
				isMobile:true
			
			}
		}
	});
	});

//删除单条记录
function delBasbook(id) {
	var url = ctxPath + "/rest/basaddressbook/delete/" + id;
	if (confirm("确定要该条记录吗？")) {
		$.get(url, null, function(data) {
			alert(data.message);
			if (data.status == "success") {
				location.href = ctxPath + "/rest/basaddressbook/list";
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
	var arrChecked = $(".check_li:checked");
	if (arrChecked.length == 0) {
		alert('请选择要删除的记录！');
		return;
	}
	var records = new Array();
	$(arrChecked).each(function() {
		records.push(this.value);
	});
	var url = ctxPath + "/rest/basaddressbook/delete/multiple";
	if (confirm("确定要删除选中的记录么？")) {
		$.get(url, "records=" + records, function(data) {
			if(data.status='success'){
				alert(data.message);
				location.href = ctxPath + "/rest/basaddressbook/list";
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
function newBasbook(){
	location.href = ctxPath + "/rest/basaddressbook/post/pre"
}

//修改
function editBasbook(id){
	location.href = ctxPath + "/rest/basaddressbook/"+id+"/put/pre";
}

//==保存信息
function saveBasbook(){
	$("#bookForm").submit();

}




	//取消
	function cancel(){
		location.href = ctxPath + "/rest/basaddressbook/list";
	}
    
	//各个部门的批量选择
	function checkAll(id){
		var li = id.split("_");
		var checkedOpt = document.getElementsByName("check_li_"+li[2]);
		for(var i=0; i<checkedOpt.length;i++){
			checkedOpt[i].checked = !checkedOpt[i].checked;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

