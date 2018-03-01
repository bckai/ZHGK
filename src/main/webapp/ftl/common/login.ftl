<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<title>${web_title!''}</title>
<script src="${ctx.contextPath}/webUI/js/jquery-1.9.1.min.js"></script>
<script src="${ctx.contextPath}/webUI/js/jquery.lazyload.min.js"></script>

<script src="${ctx.contextPath}/webUI/js/rb.js"></script>
<link href="${ctx.contextPath}/webUI/css/rb.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx.contextPath}/js/common/MD5.js"></script>
<script src="${ctx.contextPath}/webUI/js/dump.js"></script>

<style>
body{
	background-color:#ce203b;
}

.login_defalut_bg {
	background-image:url(${ctx.contextPath}/webUI/login_bg_ani/TheAurora6.jpg);
	background-repeat:no-repeat;
	background-size:100%;
	width:100%;
	height:100%;
	position:absolute;
	z-index:9998;
}

.login_bg {
	background-repeat:no-repeat;
	background-size:cover;
	width:100%;
	height:100%;
	position:absolute;
}
.login_div{
	position:absolute;
	z-index:9999;
	background-image:url(${ctx.contextPath}/webUI/assets/login_mask_bg.png);
	background-repeat:repeat;
	width:40%;
	height:100%;
	margin-left:30%;
}

.login_text{
	background-image:url(${ctx.contextPath}/webUI/assets/login_logo_text.png);
	background-repeat:no-repeat;
	background-size:contain;
	background-position:center;
	width:100%;
	height:40%;
	margin-bottom:10%;
}
.login_input{
	width:70%;
	height:35px;
	padding:5px 15px 5px 15px;
	margin-top:10px;
	margin:auto;
	border-radius:30px;
	background-color:#FFF;
	margin-bottom:5%;
}

.login_input img{
	width:35px;
	height:35px;
	border:0px;	
	position:absolute;
}

.login_input img:last-child{
	width:25px;
	height:25px;
	margin-top:5px;
	visibility:hidden;
	float:right;
}

.login_input input{
	color:#666;
	font-size:20px;
	line-height:35px;
	border:0px;
	outline:none;
	width:auto;
	margin:0px;
	float:left;
	width:100%;
	text-align:center;
}
.login_input input:hover{	
	cursor:pointer;	
}

.login_button{
	width:70%;
	height:35px;
	line-height:35px;
	padding:5px 15px 5px 15px;
	margin-top:10px;
	margin:auto;
	border-radius:30px;
	background-color:#fe1254;
	text-align:center;
	margin-bottom:5%;
	font-size:20px;
	color:#FFF;

}

.login_button:hover{
	background-color:#e16bff;
	cursor:pointer;	
}
</style>
<script>
$(document).ready(function() {
	$(".login_input").find("img:first-child").css("left",($(".login_div").width()-35)/2+"px");
	$(".login_input").find("img:last-child").css("right",$(".login_div").width()*0.15+"px");
	$(".login_input").find("input").focus(function(e) {
		var leftint = $(".login_div").width()*0.15;
		$(this).parent().find("img:first-child").animate({left: leftint+"px"},500);
    });
	
	$(".login_input").find("input").keyup(function(e) {
		if ($(this).val().length>=1){
			$(this).parent().find("img:last-child").css("visibility","visible");
		}else{
			$(this).parent().find("img:last-child").css("visibility","hidden");
		}
    });
	$(".login_input").find("img:last-child").click(function(e) {
        $(this).parent().find("input").val("");
		$(this).parent().find("img:first-child").animate({left:($(".login_div").width()-35)/2+"px"},500);
		$(this).css("visibility","hidden");
    });
	$(".login_button").click(function(e) {
		login();
    });
	
	$(".login_input").find("input").blur(function(e) {
        if ($(this).val().length<=1){
			$(this).parent().find("img:first-child").animate({left: ($(".login_div").width()-35)/2+"px"},500);
		}
    });
	
	//回车事件
	document.onkeydown = function(e){ 
    	var ev = document.all ? window.event : e;
    	if(ev.keyCode==13) {
			login();
    	}
	}
	createBg();
	
});
//登录事件
function login(){
	var username = $("#username").val();
	var password = hex_md5($("#password").val());
	if (username.length>0){
		var url="${ctx.contextPath}/rest/default/login";
		$.post(url,{"username":username,"password":password,"isUseName":1,"isPWD":1},function(json){
			if (json.respCode == "success"){
				RB.Sstrorage(true,'realName',json.message);						
				RB.Toast("登录成功，进入系统中...");
				setTimeout("window.location.href='${ctx.contextPath}"+json.respList[0].url+"'",2000);
			}else{
				RB.AlertInfo("登录失败",json.message);
			}
		});
	}else{
		RB.Toast("用户名不能为空!");
	}	
}

$(function() {    
	$("img:below-the-fold").lazyload({  
		event : "sporty"   
	});    
}); 
   
$(window).bind("load", function() {    
	var timeout = setTimeout(function() {$('img').lazyload();animinBg();}, 1000);    
}); 
/*
//创建动画
var bgi = 1;
var bgm = 129;
var anis;
function createBg(){
	for (var i=bgi;i<=bgm;i++){
		$("#login_ani").append("<div class='login_bg' id='ani_"+i+"'><img data-original='${ctx.contextPath}/webUI/login_bg_ani/TheAurora"+i+".jpg' height='"+BODYHEIGHT+"px' width='"+BODYWIDTH+"px' data='${ctx.contextPath}/webUI/login_bg_ani/TheAurora6.jpg' /> </div>");
		$("#ani_"+i).css("z-index" , 9998-i);		  
		if (i == bgm){
			$(".login_defalut_bg").css("display","none");
		}
	}
}
//运行动画
function animinBg(){
	$("#ani_"+bgi).css("visibility","hidden");
	anis = setTimeout("animinBg()",150);
	bgi ++ ;
	if (bgi == bgm){
		//clearTimeout(anis);
		$("#login_ani div").css("visibility","visible");
		bgi = 1;
	}
}*/
</script>
</head>

<body>
	<div class="login_div">
    	<div class="login_text"></div>
        <div class="login_input"><img src="${ctx.contextPath}/webUI/assets/login_input_user.png" /><input type="text" id="username" /><img src="${ctx.contextPath}/webUI/assets/login_input_clear.png" /></div>
        <div class="login_input"><img src="${ctx.contextPath}/webUI/assets/login_input_pass.png" /><input type="password" id="password"/><img src="${ctx.contextPath}/webUI/assets/login_input_clear.png" /></div>
        <div class="login_button">登 录</div>
    </div>
    <div class="login_defalut_bg"></div>
    <div id="login_ani">  	
    </div>
</body>
</html>

