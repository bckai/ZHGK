<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${web_title!}</title>
		<script src="${ctx.contextPath}/webUI/js/jquery-1.9.1.min.js"></script>
	</head>	
	<frameset cols="200,*" frameborder="no" border="0" framespacing="0" id="mainFrame">
	    <frame src="${ctx.contextPath}/rest/default?m=mainMenu" name="leftFrame" scrolling="no" noresize="noresize" />
	    <frame src="${ctx.contextPath}/rest/default?m=center" name="rightFrame" id="rightFrame" scrolling="auto"/>
	</frameset>
	<noframes>
		<body>
			<center>您的浏览器版本太低，请升级浏览器！</center>
		</body>
	</noframes>
</html>
