var ctxPath;
$(document).ready(function(){
	//==初始化contextPath
	ctxPath = $('#ctxPath').val();
});


// ==删除组织结构
function deleteResource(id,modId) {
	 var url = ctxPath + '/rest/resource/' + id;
     if (confirm('确定要删除该条记录吗？')) {
     	$.deleteJSON(url,function(data){
     		if(data.status=="success"){
     			alert('删除成功！');
     			location.href = ctxPath + '/rest/module/'+modId+'/resource/get/pre';  
     		}else{
     			alert('删除失败！');
     		}
     });
}
}


// ==保存用户信息
function saveResource() {
	if($('#resourceForm').form('validate')){
		$('#resourceForm').submit();
	}
}
