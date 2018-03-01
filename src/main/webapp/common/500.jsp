<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory"%>
<%
	response.setStatus(200); // 200 = HttpServletResponse.SC_OK
%>
<%
	Throwable ex = null;
	if (exception != null)
		ex = exception;
	if (request.getAttribute("javax.servlet.error.exception") != null)
		ex = (Throwable) request.getAttribute("javax.servlet.error.exception");

	//记录日志
	Logger logger = LoggerFactory.getLogger("500.jsp");
	logger.error(ex.getMessage(), ex);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>500 - 系统内部错误</title>
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
			系统发生内部错误.如有疑问请和管理员联系，请<a href="javascript:void(0);"
				onclick="parent.location.href='<%=request.getContextPath()%>/rest/default/logout'">返回首页</a>
		</div>
	</div>
</body>
</html>