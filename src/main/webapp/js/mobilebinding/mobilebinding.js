/**
 * mobilebinding 页面使用javascript
 * @copyright {@link www.hndfsjsoft.com}
 * @author TYWH  <Auto generate>
 * @version  2013-05-15 16:17:19
 */

var ctxPath;

$(document).ready(function(){
    ctxPath = $('#ctxPath').val();
	$('input[type=checkbox]').click(function(e){
		e.stopPropagation();
    });
    $("input[type=checkbox]").each(function(){
        $(this).attr("checked", false);
    });
    // 设置全选
    $("#check_all").click(function(){
        $("input[name='check_li']").each(function(){
            $(this).attr("checked", !this.checked);
        });
    });
});

function saveMobileBinding(){
	if($('#bindingForm').form('validate')){
	    var url = ctxPath + "/rest/mobilebinding/save";
	    $.post(url, $('#bindingForm').serialize(), function(data){
	        if (data.status == "success") {
	            alert(data.message);
	            location.reload();
	        }
	        else {
	            alert(data.message);
	        }
	    });
	}
}

function editMobileBinding(id){
    var url = ctxPath + "/rest/mobilebinding/" + id + "/put/pre";
    location.href=url;
}

function delMobileBinding(id){
    var url = ctxPath + "/rest/mobilebinding/delete/" + id;
    if (confirm("确定要删除么？")) {
        $.get(url, null, function(data){
            if (data.status == "success") {
                alert(data.message);
                location.reload();
            }
            else {
                alert(data.message);
            }
        });
    }
}

function checkThis(tr){
    if ($(tr).children().first().children().attr("checked")) 
        $(tr).children().first().children().attr("checked", false);
    else 
        $(tr).children().first().children().attr("checked", true);
}


function delMulti(){
    var arrChecked = $("input[name='check_li']:checked");
    if (arrChecked.length == 0) {
        alert('未选中任何记录！');
        return;
    }
    var records = new Array();
    $(arrChecked).each(function(){
        records.push(this.value);
    });
	var url = ctxPath + "/rest/mobilebinding/delMulti";
    if (confirm("记录删除后将不能恢复，确定要删除选中的记录么？")) {
        $.get(url, "records=" + records, function(data){
            alert(data.message);
            location.reload();
        });
    }
}

function stopBubble(e){
    if (e.stopPropagation) {
        e.stopPropagation();
        e.preventDefault();
    }
    else {
        e.returnValue = false;
        e.cancelBubble = true;
    }
}

function infoMobileBinding(id){
    openInfoDialog(id);
    return false;
}

function openInfoDialog(id){
    var url = ctxPath + "/rest/mobilebinding/" + id;
    $.get(url, null, function(data){
        $('#infoDialog').html(data);
        $('#infoDialog').dialog('open');
    });
}

function openDialog(){
    $('#newDialog').dialog('open');
	$('#newDialog').css("width","100%");
}

/**
function validateRules(form){
    $("#" + form).validate({
        rules: {
            userName: {
                required: true,
                userName: true,
                minlength: 6,
                maxlength: 12
            }
            userId: "required"
        }
    });
}
**/
