<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>商城会员注册页面</title>
		<%@include file="/pages/common/header.jsp"%>
		<script type="text/javascript">

			//加载页面
			$(function () {
				//绑定鼠标失去焦点事件,使用ajax请求验证用户名是否可用
				$("#username").blur(function () {
					var username = this.value;
					$.getJSON("http://localhost:8080/book-mall/userServlet","action=ajaxIsExitUserName&username="+username,function (data) {
						if (data.existUser) {
							$("span.errorMsg").text("用户名已存在!");
						} else {
							$("span.errorMsg").text("用户名可用!");
						}
					});
				});
				// 给注册绑定单击事件
				$("#sub_btn").click(function () {
					// 验证用户名：必须由字母，数字下划线组成，并且长度为5到12位
					//1 获取用户名输入框里的内容
					var usernameText = $("#username").val();
					//2 创建正则表达式对象
					var usernamePatt = /^\w{5,12}$/;
					//3 使用test方法验证
					if (!usernamePatt.test(usernameText)) {
						//4 提示用户结果
						$("span.errorMsg").text("用户名不合法！");

						return false;
					}
					//验证密码
					var passwordtest = $("#password").val();
					//创建密码正则表达式,长度为5-12位
					var passwordPatt = /^\w{5,12}$/;
					//判断输入的密码否合法
					if (!passwordPatt.test(passwordtest)) {
						//给用户提示
						$("span.errorMsg").text("输入的密码不合法");
						return false;
					}
					//确认密码
					var repwdtest = $("#repwd").val();
					//判断上一次输入的密码是否和yizhi
					if (repwdtest != passwordtest){
						$("span.errorMsg").text("两次输入密码的不一致");
						return false;
					}
					//验证邮箱
					var emailtest = $("#email").val();
					//创建正则表达式对象
					var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
					//判断邮箱是否合法
					if (!emailPatt.test(emailtest)) {
							$("span.errorMsg").text("输入的邮箱不合法");
							return  false;
					}
					//验证验证码,有时候验证码不清楚,需要点击验证码刷新得到一张新的验证码
					$("#codepulsh").click(function () {
						this.src = "${basePath}kaptcha.jpg?d=" + new Date();
					});
				});
			});
		</script>

	<style type="text/css">
		.login_form{
			height:420px;
			margin-top: 25px;
		}
	
	</style>
	</head>
	<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/w.jpg">
		</div>

			<div class="login_banner">

				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>

				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册书城会员</h1>
								<span class="errorMsg">
									<%=request.getAttribute("msg")==null ? "" : request.getAttribute("msg")%>
									${requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<!--web.xml配置路径,隐藏域将注册和登录一起处理,坑:value后跟要处理的具体方法名
									name="action"不能丢-->
									<input type="hidden" name="action" value="register"/>
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   <%--表单回显,密码错误用户名依然在输入框--%>
										   value="${requestScope.username}"
										   autocomplete="off" tabindex="1" name="username" id="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   value="${requestScope.email}"
										   autocomplete="off" tabindex="1" name="email" id="email" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" name="code" type="text" style="width: 80px;" id="code"/>
									<img id="codepulsh" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px; width: 110px; height: 30px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		<%@include file="/pages/common/footer.jsp"%>
	</body>
</html>