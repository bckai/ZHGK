<%@ page contentType="text/html;charset=UTF-8"%>
<%
	response.setStatus(200); // 200 = HttpServletResponse.SC_OK
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>404 - 页面不存在</title>
<style>
body {
	background: #F2F2F2;
	padding-top: 30px;
}

.error {
	background: url(<%=request.getContextPath()%>/images/ui20120918_19.jpg)
		center top no-repeat;
	width: 500px;
	padding: 160px 0 0 0;
	margin: 10% auto 0;
}

.error div {
	font-size: 14px;
	color: #000;
	line-height: 24px;
	text-align: center;
	width: auto;
	background: #FCFCFC;
	border-left: #DB5657 5px solid;
	padding: 8px 20px;
}

.error div a {
	font-size: 14px;
	color: #06C;
	line-height: 24px;
	font-weight: bold;
}

.error div a:hover {
	color: #C00;
}
</style>

</head>

<body>
	<div class="error">
		<div>
			很抱歉，您要访问的页面不存在，请<a href="javascript:void(0);"
				onclick="parent.location.href='<%=request.getContextPath()%>/rest/default/logout'">返回首页</a>
		</div>
	</div>
</body>
</html>
