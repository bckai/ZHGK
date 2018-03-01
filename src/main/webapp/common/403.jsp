<%@ page contentType="text/html;charset=UTF-8"%>
<%
	response.setStatus(200); // 200 = HttpServletResponse.SC_OK
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>403 - 缺少权限</title>
<style>
body {
	background: #F2F2F2;
	padding-top: 30px;
}

.error {
	background: url(<%=request.getContextPath()%>/images/ui20120918_32.jpg)
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
	<div>
		<div class="error">
			<div>
				很抱歉,你没有访问该页面的权限。<a href="javascript:void(0);"
					onclick="history.go(-1)">返回</a>
			</div>
			<div><%=request.getAttribute("rw_admin_errormsg_in_requst")%></div>
		</div>
</html>