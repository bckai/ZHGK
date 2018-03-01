<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript">
			function loginOut(){
				if(window.confirm("确定要退出系统？")){
					parent.window.location.href="${ctx.contextPath}/rest/default/logout";
					parent.window.close();
				}
			}
			
			function cleanCss(){
				var leftFrameA = parent.leftFrame.document.getElementsByTagName('a');
				for(var i =0; i < leftFrameA.length; i++){
					if(leftFrameA[i].className == 'current'){
						leftFrameA[i].className = '';
					}
				}
			}
			function loginIndex(){
				parent.rightFrame.window.location = "${ctx.contextPath}/rest/default?m=center";
			}
		</script>
		<style type="text/css">
			* { padding:0; margin:0;}
			a, button {
			 outline: none; /*適用Firefox*/
			 hlbr:expression(this.onFocus=this.blur()); /*適用IE*/
			 }
			
			body { background:url(${ctx.contextPath}/images/ui20120918_01.jpg) repeat-x;}
			//.logo { background:url(${ctx.contextPath}/images/common/logo_top.png) no-repeat; width:530px; height:60px; float:left; margin:10px 0px;}
			.logo { background:url(${ctx.contextPath}/images/ui20120918_02.jpg) no-repeat; width:530px; height:80px; float:left; }
			.panel { float:right; list-style:none;}
			.panel li { float:left; width:62px; height:70px; margin:5px 5px 0; }
			.panel li a { display:block; width:62px; height:30px; font-size:12px; text-align:center; font-weight:bold; text-decoration:none; color:#000; padding-top:46px;}
			.panel li a:hover { color:#C00;}
			.panel li a.link_1 { background:url(${ctx.contextPath}/images/ui20120918_08-05.jpg) no-repeat;}
			.panel li a.link_2 { background:url(${ctx.contextPath}/images/ui20120918_14.jpg) no-repeat;}
			.panel li a.link_3 { background:url(${ctx.contextPath}/images/ui20120918_16.jpg) no-repeat;}
			.panel li a.link_4 { background:url(${ctx.contextPath}/images/ui20120918_18.jpg) no-repeat;}
			.panel li a.link_5 { background:url(${ctx.contextPath}/images/ui20120918_09.jpg) no-repeat;}
			.panel li a.link_6 { background:url(${ctx.contextPath}/images/ui20120918_10.jpg) no-repeat;}
			.panel li a.link_7 { background:url(${ctx.contextPath}/images/ui20120918_03-02.jpg) no-repeat;}
			.panel li a.link_8 { background:url(${ctx.contextPath}/images/ui20120918_03-04.jpg) no-repeat;}
			.userInfo { background:url(${ctx.contextPath}/images/ui20120918_25.jpg) no-repeat; width:100%; height:41px; overflow:hidden; float:left; font-size:12px; color:#FFF;}
			.userInfo div { float:left; line-height:42px; margin:0 10px;}
			.userInfo .user { background:url(${ctx.contextPath}/images/ui20120918_27.jpg) 0 13px no-repeat; text-indent:20px;}
			.userInfo .date { background:url(${ctx.contextPath}/images/ui20120918_28.jpg) 0 13px no-repeat; text-indent:23px;}
		</style>
	</head>
	<body>
		<input type="hidden" id="ctx" value="${ctx.contextPath}" />
		<div class="logo"></div>
		<label id="clickTab" class=""></label>
		<ul class="panel">
		    <li>
		    	<a class="link_8" href="#" onclick="loginIndex()">首页</a>
		    </li>
			<#list moduleList as m>
				<#if !m.superMod??>
					<#if m.name=="流程管理"><li id="top_${m_index}"><a href="${ctx.contextPath}/rest/default?m=left&superMid=${m.id}" target="leftFrame"  class="link_1">${m.name}</a></#if>
					<#if m.name=="应用管理"><li id="top_${m_index}"><a href="${ctx.contextPath}/rest/default?m=left&superMid=${m.id}" target="leftFrame"  class="link_7">${m.name}</a></#if>
					<#if m.name=="考勤定位"><li id="top_${m_index}"><a href="${ctx.contextPath}/rest/default?m=left&superMid=${m.id}" target="leftFrame"  class="link_7">${m.name}</a></#if>
					<#if m.name=="信息公告"><li id="top_${m_index}"><a href="${ctx.contextPath}/rest/default?m=left&superMid=${m.id}" target="leftFrame"  class="link_6">${m.name}</a></#if>
					<#if m.name=="系统权限"><li id="top_${m_index}"><a href="${ctx.contextPath}/rest/default?m=left&superMid=${m.id}" target="leftFrame"  class="link_3" onclick="javascript:document.getElementById('clickTab').className='current';">${m.name}</a></li></#if>
				</#if>
			</#list>
		    <li>
		    	<a class="link_5" href="#" onclick="loginOut()">退出系统</a>
		    </li>
		</ul>
		<div class="userInfo">
			<div class="user">
				欢迎您：
				<a href="${ctx.contextPath}/rest/user/own/info/update/put/pre" target="rightFrame" onclick="cleanCss();">
					${account.realName?default("")}
				</a>
			</div>
		    <div class="date">当前日期：${date!} ${week!}</div>
		</div>
	</body>
</html>