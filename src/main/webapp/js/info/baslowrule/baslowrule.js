/**
 * baslowrule 页面使用javascript
 * @copyright {@link www.hndfsjsoft.com}
 * @author Mr.Hao<Auto generate>
 * @version  2013-06-10 12:11:15
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
  //表单必填验证
    if(document.getElementById('LowruleForm')){
    	$('#LowruleForm').validate({
    		rules:{
	    		title : { required : true,
	    		          maxlength : 50
	    	        }
    	}
    	});
    };
});

function newLowrule(){
    location.href = ctxPath+"/rest/baslowrule/post/pre";
}

function saveLowrule(form){
	document.getElementById("context").value=editor.html();
    $('#LowruleForm').submit();
}

function publish(id){
	if(confirm("确认要发布此条法规？")){
	   var url= ctxPath+"/rest/baslowrule/publish/"+id;
	   $.get(url,null,function(data){
		   if(data.status=="success"){
			   location.reload();
		   }else{
			   alert("发布失败！");
		   }
	   });
	}
}

function editBasLowrule(id){
    location.href = ctxPath + "/rest/baslowrule/" + id + "/put/pre";
}

function delBasLowrule(id){
    var url = ctxPath + "/rest/baslowrule/delete/" + id;
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
	var url = ctxPath + "/rest/baslowrule/delMulti";
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



