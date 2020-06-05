<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<%--将base.jsp页面中的base标签、link标签、script标签包含进来--%>
	<%@include file="/WEB-INF/include/base.jsp"%>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
	<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
	<script type="text/javascript">
		$(function () {
			//给注册按钮绑定单击事件
			$("#sub_btn").click(function () {
				//获取用户输入的用户名
				var username = $("#username").val();
				//设置验证用户名的正则表达式
				//用户名必须是3-6位的，而且只能使用数字、字母、下划线或减号
				var usernameReg = /^[a-zA-Z0-9_-]{3,6}$/;
				//验证用户名是否符合要求
				var flag = usernameReg.test(username);
				if(!flag){
					alert("请输入3-6位的字母、数字、下划线或减号的用户名！");
					return false;
				}
				//获取用户输入的密码
				var password = $("#password").val();
				//设置验证密码的正则表达式
				var passwordReg = /^[a-zA-Z0-9_-]{6,18}$/;
				//验证密码是否符合要求
				if(!passwordReg.test(password)){
					alert("请输入6-18位的字母、数字、下划线或减号的密码！");
					return false;
				}
				//获取用户输入的确认密码
				var repwd = $("#repwd").val();
				//验证两次输入的密码是否一致
				if(repwd != password){
					//清空确认密码，即设置确认密码框的value值为空串
					$("#repwd").val("");
					alert("两次输入的密码不一致！");
					return false;
				}
				//获取用户输入的邮箱
				var email = $("#email").val();
				//设置验证邮箱的正则表达式
				var emailReg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
				//验证邮箱是否符合规则
				if(!emailReg.test(email)){
					alert("邮箱格式不正确！");
					return false;
				}
				//获取用户输入的验证码
				var code = $("#code").val();
				if(code == ""){
					alert("验证码不能为空!");
					return false;
				}
			});
			//给验证码绑定单击事件
			$("#codeImg").click(function () {
				//当img标签中的src的路径发生变化时，浏览器会重新向src的新路径发送请求
				$(this).attr("src","code?t="+Math.random());
			});

			//给文本框绑定change事件
			$("#username").change(function () {
				//获取用户输入的用户名
				var username = $(this).val();
				//设置验证用户名的正则表达式
				//用户名必须是3-6位的，而且只能使用数字、字母、下划线或减号
				var usernameReg = /^[a-zA-Z0-9_-]{3,6}$/;
				if(!usernameReg.test(username)){
					alert("请输入3-6位的字母、数字、下划线或减号的用户名！")
					return false;
				}
				//设置请求地址
				var url = "UserServlet?methodName=checkUsernameByAjax";
				//设置请求参数
				// var param = "username="+username;
				var param = {"username":username};
				$.post(url,param,function (res) {
					//将响应信息设置到span标签中
					$("#error").html(res);
				},"text");
			});
		});
	</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg" id="error">${requestScope.msg}</span>
							</div>
							<div class="form">
								<form action="UserServlet?methodName=regist" method="post">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px;" id="code" name="code"/>
									<img id="codeImg" alt="" src="code" style="float: right; margin-right: 40px;width: 80px;height: 40px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>