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


//验证账户唯一
function checkOnly(){
	var oldName = document.getElementById("oldName").value;
	oldName = oldName==""?null:oldName;
	$.putJSON(ctxPath + '/rest/role/' + oldName + '/validator',$('#roleForm').serialize(),function(resData){
		if(resData.status != "success"){
			alert('该角色名已存在！');
			checkKey = false;
		}else{
			checkKey = true;
		}
	});
}

//==保存角色信息
function saveRole(){
	checkOnly();
	$('#roleForm').submit();
}

//=保存分配结果
function saveResAssign(roleId){
	//==生成选择的资源ID列表字符串
	var resourceIds="";
	var nodes = $('#resTree').tree('getChecked');
	for(var i=0; i<nodes.length; i++){
		node = nodes[i];
		if ($('#resTree').tree('isLeaf', node.target)) {
			if (resourceIds != '') resourceIds += ',';
			resourceIds += node.id;
		}
	}

	//==提交请求
	var url = $('#ctxPath').val()+'/rest/role/'+roleId+'/resource/assign';	
	$.postJSON(url,{roleId:roleId,resourceIds:resourceIds},function(resData){
		if(resData.status == "success"){
			alert('资源分配成功！');
			location.href=ctxPath+'/rest/role/get/pre';
		} else {
			alert('资源分配失败！');
		}
	});
}


//==删除角色
function deleteRole(id){
            var url = ctxPath + '/rest/role/' + id;
                if (confirm('确定要删除该条记录吗？')) {
                	$.deleteJSON(url,function(data){
                		if(data.status=="success"){
                			alert('删除成功！');
                			location.href = ctxPath + '/rest/role/get/pre';  
                		}else{
                			alert('删除失败！');
                		}
                });
       }
}   

