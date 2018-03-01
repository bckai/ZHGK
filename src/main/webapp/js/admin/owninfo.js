/**
 * 个人信息维护JS
 *
 * @author ibm
 */

//==初始化时创建新增和修改的窗口对象
var winPass;
var formPass;
var ctxPath;

$(document).ready(function(){
	//==初始化contextPath
	ctxPath = $('#ctxPath').val();
});



//==保存个人信息
function saveOwnInfo(){
	var formOwnInfo = $('#ownForm');
	formOwnInfo.form('submit',{
		onSubmit:function(){
			if (formOwnInfo.form('validate')){
				$.putJSON(ctxPath+'/rest/user/own/info/update', formOwnInfo.serialize(),function(resData){
					if(resData.status == "success"){
						alert(resData.message);
					} else {
						alert(resData.message);
					}});
			}
			return false;
		}
	});
}

//修改用户密码
function updatePwd(){
	location.href = ctxPath + "/rest/user/password/update/put/pre";
}











