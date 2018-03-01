<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>${web_title!}</title>
<script src="${ctx.contextPath}/webUI/js/jquery-1.9.1.min.js"></script>

<script src="${ctx.contextPath}/webUI/js/jquery.mCustomScrollbar.concat.min.js"></script>
<link href="${ctx.contextPath}/webUI/css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
<link href="${ctx.contextPath}/webUI/css/rb.css" rel="stylesheet" type="text/css">
<script>
$(document).ready(function() { 
	//左侧菜单启动滚动条
	$(".rb_mainmenu").mCustomScrollbar();
	//默认第一个菜单选中
	$(".rb_secondmenu ul li").first().css("background-color","#4dbd74");
	//监听菜单点击改变背景
	$(".rb_secondmenu ul li").click(function(){
		$(".rb_secondmenu ul li").each(function(){
			$(this).css("background-color","transparent");
        });
		$(this).css("background-color","#4dbd74");
	});
	
});

//切换logo背景
function changeBgColor(val){
	$(".rb_logo").css("background-color", val);
	
}




</script>
<style>
a:visited{
	text-decoration:none;
	color:#fff;
}
a:hover{
	text-decoration:none;
	color:#fff;
}
a:active{
	text-decoration:none;
	color:#fff;
}
a:link{
	text-decoration:none;
	color:#fff;
}

</style>
</head>

<body>
    <div class="rb_leftmenu">
        <div class="rb_logo"></div>
        <div class="rb_mainmenu">
        	<#list moduleList as list>
        	<#if list.isValid==1>
            <div class="rb_firstmenu" id="rb_firstmenu_1">
                <img src="${ctx.contextPath}/webUI/assets/temp_icon_${list_index+1}.png">${(list.name)!""}
            </div>
            <div class="rb_secondmenu" id="rb_secondmenu_1">
                <ul>
                	<#list list.children as child>
                		<#if child.isValid==1>
	                    	<a href="${ctx.contextPath}${(child.defaultURL)!''}" target="rightFrame" onClick="changeBgColor('#6e8187');"><li><img src="${ctx.contextPath}/webUI/assets/temp_icon_second.png">${(child.name)!""}</li></a>
                		</#if>
                 	</#list>
                </ul>
            </div>
        		</#if>
            </#list>
        </div>
        <div class="rb_person">
        	<div class="rb_person_tx"></div>
            <div class="rb_person_text">欢迎!${account.realName?default("")}</div>
        </div>
        
    </div>
</body>
</html>
