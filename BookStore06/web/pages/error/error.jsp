<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>错误页面</title>
	<%--将base.jsp页面中的base标签、link标签、script标签包含进来--%>
	<%@include file="/WEB-INF/include/base.jsp"%>
</head>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">出错啦！</span>
		<%@ include file="/WEB-INF/include/welcome.jsp"%>
	</div>
	
	<div id="main">
			<br><br><br><br><br><br><br><br><br>
			<h1 align="center">系统出现异常！快联系<a style="color: red" href="index.jsp">管理员</a>吧！</h1>
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>