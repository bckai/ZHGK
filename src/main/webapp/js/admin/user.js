var ctxPath;
$(document).ready(function(){
	//==初始化contextPath
	ctxPath = $('#ctxPath').val();
	if(document.getElementById('msg')&&document.getElementById('msg').value){
		alert('提示信息！',document.getElementById('msg').value,'info');
	}
	if($('#depId')){
		$('#depId').combotree({url: ctxPath + '/rest/dept/combotree/public',method:'get'});
		$('#depId').combotree('setValue',$('#depVal').val());
	}
});


function deleteUser(id) {
	 var url = ctxPath + '/rest/user/' + id;
     if (confirm('确定要删除该条记录吗？')) {
     	$.deleteJSON(url,function(data){
     		if(data.status=="success"){
     			alert('删除成功！');
     			location.href = ctxPath + '/rest/user/get/pre';  
     		}else{
     			alert('删除失败！');
     		}
     });
}
}

function checkOnly(){
	var checkKey = false;
	var oldName = document.getElementById("oldName").value;
	oldName = oldName==""?null:oldName;
	$.putJSON(ctxPath+'/rest/user/' + oldName + '/validator',$('#userForm').serialize(),function(resData){
		if(resData.status=="success"){
			checkKey=true;
		}else{
			alert('该用户名已存在！');
			checkKey=false;
		}
	});
	return checkKey;
}


function reSetPwd(id){
	confirm('确定要重置该用户密码？', function(r) {
		if(r){
			alert('密码重置成功！');
			location.href = ctxPath + '/rest/user/' + id+"/password/reset";
		}
	});
}

// ==保存用户信息
function saveUser() {
	var oldName = document.getElementById("oldName").value;
	oldName = oldName==""?null:oldName;
	$.putJSON(ctxPath+'/rest/user/' + oldName + '/validator',$('#userForm').serialize(),function(resData){
		if(resData.status=="success"){
			$('#userForm').submit();
		}else{
			alert('该用户名已存在！');
		}
	});
//	var flag = checkOnly();
//	alert(flag);
//	if(flag){
//		$('#userForm').submit();
//	}
}
/*
 * ==============================================================================
 * 分配角色应用
 * ==============================================================================
 */

// ==角色列表选择事件处理
function roleSelect(src, des, all) {
	var src = document.getElementById(src);
	var des = document.getElementById(des);

	// ==单项或多项选择处理
	if (!all) {
		while (src.selectedIndex > -1) {
			var text = src.options[src.selectedIndex].text;
			var value = src.options[src.selectedIndex].value;
			src.remove(src.selectedIndex);
			var newOption = document.createElement("OPTION");
			newOption.text = text;
			newOption.value = value;
			des.options.add(newOption);
		}
	}
	// ==全部选择处理
	else {
		for ( var i = 0; i < src.length; i++) {
			var text = src.options[i].text;
			var value = src.options[i].value;
			var newOption = document.createElement("OPTION");
			newOption.text = text;
			newOption.value = value;
			des.options.add(newOption);
		}
		src.options.length = 0;
	}
}


//==分配角色保存
function saveRoleAssign() {
	var userId = document.getElementById('userId').value;
	// ==获取分配的role
	var desRoles = document.getElementById('desRole');
	var roleIds = "";
	for ( var k = 0; k < desRoles.length; k++) {
		roleIds += desRoles.options[k].value + ",";
	}
	// ==提交
	$.postJSON(ctxPath + "/rest/user/" + userId + "/role/assign", {
		roleIds : roleIds
	}, function(resData) {
		if (resData.status == "success") {
			alert('角色分配成功');
		} else if(resData.status == "warning")  {
			alert('角色分配失败:'+resData.message);
		}
	});
}

//==密码修改保存
function savePwd(){
	if($('#pwdForm').form('validate')){
		if($('#oldpassword').val() == $('#newpassword').val()){
			$.messager.confirm('提示信息', '您所输入的原始密码和新密码相同,您确定要继续“修改”？', function(r) {
				if(r) {
					$.putJSON(ctxPath+'/rest/user/password/update',$('#pwdForm').serialize(),function(resData){
						if(resData.status == "success"){
							alert('密码修改成功！');
						}else{
							alert('密码修改失败！');
						}
					});
				} else {
					return;
				}
			});
		} else {
			$.putJSON(ctxPath+'/rest/user/password/update',$('#pwdForm').serialize(),function(resData){
				if(resData.status == "success"){
					alert('密码修改成功！');
				}else{
					alert('密码修改失败！');
				}
			});
		}	
	}
}

function getNextSibling(n){   
    var x = n.nextSibling;   
    if(!x) return null;   
    while (x && x.nodeType != 1){   
        x = x.nextSibling;   
    }   
    return x;   
}  

function searchUser(deptId){
	$('#deptId').val(deptId);
	document.getElementById('userForm').submit();
}

//密码重置
function resetPWD(id){
	if(confirm("确定要重置密码？")){
		location.href = ctxPath + "/rest/user/"+id+"/password/reset?pageIndex="+$("#pageIndex").val();
		alert("系统密码已经重置");		
	}
}

//分配用户组并保存
function saveGroupAssign() {
	var userId = document.getElementById('userId').value;
	// ==获取分配的role
	var desRoles = document.getElementById('desRole');
	var groupIds = "";
	for ( var k = 0; k < desRoles.length; k++) {
		groupIds += desRoles.options[k].value + ",";
	}
	// ==提交
	$.postJSON(ctxPath + "/rest/user/" + userId + "/group/assign", {
		groupIds : groupIds
	}, function(resData) {
		if (resData.status == "success") {
			alert('用户组分配成功');
		} else {
			alert('用户组分配失败！');
		}
	});
}




























