<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会员注册页面</title>
	<%@include file="/pages/common/header.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color: #ff0000;
	}
</style>
</head>
<body>
		<div id="header">
				<img class="logo_img" alt="" src="static/img/w.jpg" >
				<span class="wel_word"></span>
				<%@include file="/pages/common/login_success.jsp"%>
		</div>
		
		<div id="main">
			<h1>注册成功! <a href="index.jsp">转到主页</a></h1>
		</div>
		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>