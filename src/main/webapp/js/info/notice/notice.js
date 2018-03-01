/**
 * 通知公告ＪＳ层
 */
var ctxPath;
var noticeWin;
$(document).ready(function() {
	ctxPath = $('#ctxPath').val();
	$('input[type=checkbox]').click(function(e) {
		e.stopPropagation();
	});
	$("input[type=checkbox]").each(function() {
		$(this).attr("checked", false);
	});

	// 设置全选
	$("#check_all").click(function() {
		$('input[name="check_li"]').attr("checked", this.checked);
	});
	var $subBox = $("input[name='check_li']");
	$subBox.click(function() {
		$("#check_all").attr("checked", $subBox.length == $("input[name='check_li']:checked").length ? true : false);
	});
	//表单验证的初始化配置
	if ($("#noticeForm")) {
		$("#noticeForm").validate( {
			rules : {
				title : {
					required : true,
					maxlength : 100
				},
				summary : {
					required : false,
					maxlength : 200
				}
			}
		});
	}
});

// 分页
function toPage(page) {
	$('#pageIndex').val(page);
	$('#noticeListForm').submit();
}

// 单击复选框
function checkThis(tr) {
	$(tr).children().first().children().attr("checked",
			!$(tr).children().first().children().attr("checked"));
}

// 发布公告
function publish(id, state) {
	var url = ctxPath + "/rest/basNotice/publish";
	if (confirm("确定要发布该条公告吗？")) {
		/*if (confirm("是否短信提醒？")) {
			$.get(url, {
				'id' : id,
				'state' : state
			}, function(data) {
				alert(data.message);
				if (data.status == "success") {
					location.reload();
				}
			});
		} else {
		
		}*/
		$.get(url, {
			'id' : id,
			'state' : state
		}, function(data) {
			if (data.status == "success") {
				location.reload();
			}
		});
	}
}

// 置顶与取消
function toTop(id, isTop) {
	var url = ctxPath + "/rest/basNotice/toTop";
	$.get(url, {
		'id' : id,
		'isTop' : isTop
	}, function(data) {
		if (data.status == "success") {
			location.reload();
		}
	});
}

// 删除单条记录
function delBasnotice(id) {
	var url = ctxPath + "/rest/basNotice/delete/" + id;
	if (confirm("确定要删除该条记录吗？")) {
		$.get(url, null, function(data) {
			if (data.status == "success") {
				location.reload();
			}
		});
	}
}

// 批量删除
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
	var url = ctxPath + "/rest/basNotice/delete/multiple";
	if (confirm("确定要删除选中的记录吗？")) {
		$.get(url, "records=" + records, function(data) {
			if(data.status=='success'){
				alert(data.message);
				location.reload();
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

var groups = new Array();
function getGroup() {
	$.getJSON(ctxPath + '/rest/group/jsonStr/public', '', function(resData) {
		if (resData.status != "success") {
			if (resData.length > 0) {
				groups = resData.split(';');
				showGroupDiv();
			} else {
				$('#groupNames').val('');
				$('#groupIds').val('');
			}
		}
	});
}

function showGroupDiv() {
	var htmlStr = ""
	for ( var i = 0; i < groups.length; i++) {
		if ($('#groupIds').val().indexOf(groups[i].split(',')[0]) > -1) {
			htmlStr += "<li class='group'>"
					+ "<input type='checkBox' onClick='checkGroup(this," + i
					+ ")' value='" + groups[i].split(',')[0] + "' checked/>"
					+ groups[i].split(',')[1] + "</li>";
		} else {
			htmlStr += "<li class='group'>"
					+ "<input type='checkBox' onClick='checkGroup(this," + i
					+ ")' value='" + groups[i].split(',')[0] + "'/>"
					+ groups[i].split(',')[1] + "</li>";
		}
	}
	document.getElementById('mainDiv').style.display = 'block';
	document.getElementById('selGroup').innerHTML = htmlStr;
}

function checkGroup(obj, i) {
	if (obj.checked) {
		$('#groupIds').val($('#groupIds').val() + groups[i].split(',')[0] + ',');
		$('#groupNames').val(
				$('#groupNames').val() + groups[i].split(',')[1] + ',');
	} else {
		$('#groupIds').val(
				$('#groupIds').val().replace(groups[i].split(',')[0] + ',', ''));
		$('#groupNames')
				.val($('#groupNames').val().replace(groups[i].split(',')[1] + ',', ''));
	}
}

function closeDiv() {
	document.getElementById('mainDiv').style.display = 'none';
}

// ==保存信息
function saveNotice() {
	document.getElementById("context").value=editor.html();
	$('#noticeForm').submit();
};