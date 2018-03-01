var ctxPath;
var checkkey = false;
$(document).ready(function() {
	ctxPath = $('#ctxPath').val();
});

// ==删除组织结构
function deleteDept(id) {
	var url = ctxPath + '/rest/dept/' + id;
    if (confirm('确定要删除该条记录吗？')) {
    	$.deleteJSON(url,function(data){
    		if(data.status=="success"){
    			alert('删除成功！');
    			location.href = ctxPath + '/rest/dept/get/pre';  
    		}else{
    			alert('删除失败！');
    		}
    });
}
}

//验证组织结构名称唯一
function checkOnly() {
	/*var oldName = document.getElementById("oldName").value;
	oldName = oldName == "" ? null : oldName;
	$.putJSON(ctxPath + '/rest/dept/' + encodeURI(oldName) + '/validator', $('#deptForm').serialize(), function(resData) {
		if (resData.status != "success") {
			alert('该名称已存在！');
			checkKey = false;
		} else {
			checkKey = true;
		}
	});*/
	return true;

}



// ==保存用户信息
function saveDept() {
	if(checkOnly()){
		$('#deptForm').submit();
	};
}

//==执行分配组织结构负责人
function saveDeptManager() {
	document.getElementById('deptManagerForm').submit();
}

/*
 * ==============================================================================
 * 分配角色应用
 * ==============================================================================
 */
function getNextSibling(n) {
	var x = n.nextSibling;
	if (!x)
		return null;
	while (x && x.nodeType != 1) {
		x = x.nextSibling;
	}
	return x;
}

/**
 * 显示隐藏节点
 * @param {Object} id
 */
function disaplay(id){
    doc = document.getElementById(id);
    var dis;
    if (doc.isDis == null) {
        doc.isDis = 1;
    }
    if (doc.isDis == 1) {
        doc.isDis = 0;
        dis = 0;
    }
    else {
        doc.isDis = 1;
        dis = 1;
    }
    while ( getNextSibling(doc) != null) {
        doc =  getNextSibling(doc);
        if ((doc.id).indexOf(id) != -1 && (doc.id).indexOf(id) == 0) {
            if (dis == 1) 
                doc.style.display = "";
            else 
                doc.style.display = "none";
        }
        else {
            break;
        }
    }
}

// =保存分配结果
function saveResAssign() {

	// ==生成选择的资源ID列表字符串
	var resourceIds = "";
	var nodes = $('#resTree').tree('getChecked');
	for ( var i = 0; i < nodes.length; i++) {
		node = nodes[i];
		if ($('#resTree').tree('isLeaf', node.target)) {
			if (resourceIds != '')
				resourceIds += ',';
			resourceIds += node.id;
		}
	}

	// ==提交请求
	var deptId = deptGrid.datagrid('getSelected').id;
	var url = $('#ctxPath').val() + '/rest/dept/' + deptId + '/resource/assign';
	$.postJSON(url, {
		deptId : deptId,
		resourceIds : resourceIds
	}, function(resData) {
		if (resData.status == "success") {
			top.msgTipSuccess(resData.message);
			closeResAssignWindow();
		} else {
			top.msgTipFail(resData.message);
		}
	});
}