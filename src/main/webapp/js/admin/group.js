/**
 * role 页面使用javascript
 * @copyright {@link www.hndfsjsoft.com}
 * @author Mrs.Zhang<Auto generate>
 * @version  2013-01-09 15:30:37
 */

var ctxPath;
var checkKey = true;
$(document).ready(function(){
    //==初始化contextPath
    ctxPath = $('#ctxPath').val();
});


//验证用户组名称唯一
function checkOnly(){
	var oldName = document.getElementById("oldName").value;
	oldName = oldName==""?null:oldName;
	$.putJSON(ctxPath + '/rest/group/' + oldName + '/validator',$('#groupForm').serialize(),function(resData){
		if(resData.status != "success"){
			alert('该用户组已存在！');
			checkKey = false;
		}else{
			checkKey = true;
		}
	});
}

//==保存用户组信息
function saveGroup(){
	checkOnly();
	$('#groupForm').submit();
}

//=保存分配结果
function saveUserAssign(groupId){
	//==提交请求
	var url = $('#ctxPath').val()+'/rest/group/'+groupId+'/assign';	
	$.postJSON(url,{userIds:$("#userIds").val()},function(resData){
		if(resData.status == "success"){
			alert('用户分配成功！');
			location.href=ctxPath+'/rest/group/get/pre';
		} else {
			alert('用户分配失败！');
		}
	});
}


// ==删除用户组
function deleteGroup(id) {
	var url = ctxPath + '/rest/group/' + id;
	if (confirm('确定要删除该条记录吗？')) {
		$.deleteJSON(url, function(data) {
			if (data.status == "success") {
				alert('删除成功！');
				location.href = ctxPath + '/rest/group/get/pre';
			} else {
				alert('删除失败！');
			}
		});
	}
}

var setting = {
	check : {
		enable : true,
		chkboxType : {
			"Y" : "s",
			"N" : "ps"
		}
	},
	view : {
		dblClickExpand : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		beforeClick : beforeClick,
		onCheck : onCheck
	}
};

function beforeClick(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.checkNode(treeNode, !treeNode.checked, null, true);
	return false;
}

function onCheck(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = zTree.getCheckedNodes(true);
	var userIds = "";
	for ( var i = 0; i < nodes.length; i++) {
		if (nodes[i].remark == "1") {
			userIds += nodes[i].id + ",";
		}
	}

	if (userIds.length > 0) {
		userIds = userIds.substring(0, userIds.length - 1);
	}
	$("#userIds").val(userIds);
}

