<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>"> 
<link href="images/pic/csgo.png" rel="shortcut icon">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>T4F 饰品商城 | 登录</title>
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css"> 
<link rel="stylesheet" href="css/head.css" type="text/css">
<script src="js/jquery-2.0.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<style type="text/css">
body {
	background: url("http://120.78.164.143:8080/images/common/login.jpg") 0px 40px no-repeat !important;
}
.content {
	margin-top: 120px;
	min-height: 600px;
}
.login-reg {
	position: relative;
	top: 50px;
	left: 150px;
	width: 300px;
}
.field {
	margin-top: 15px;
}
.field input {
	width: 260px;
	overflow: hidden;
	height: 40px;
	line-height: 40px;
	padding-left: 20px;
	background: #475063;
	border: 1px solid #696f87;
	float: left;
}
.field label {
	position: relative;
	top: 0px;
	left: 0px;
	border : 1px solid #696f87;
	border-right: 0px;
	width: 40px;
	height: 40px;
	background-color: #3d4458;
	line-height: 40px;
	text-align: center;
	float: left;
}
.validate_code {
	float: left;
	width: 120px;
	height: 40px;
}
.btn-submit {
    height: 40px !important;
    width: 300px;
    border: none;
    line-height: 40px !important;
    color: #fff;
    background: #ff9400;
    font-size: 14px !important;
    padding: 0 !important;
}
.mt-15 {
	margin-top: 15px;
}
.alert-err {
	color: #F79102;
}
.user-top {
	display:none;
}
</style>
</head>
<body>
	<%@include file="../common/head.jsp" %>
	<div class="content">
		<div class="login-reg">
			<div class="title">
				<span style="color:#e4b35a;font-size: 30px;">登录</span>
			</div>
			<div class="alert-err"></div>
			<div id="login-form">
				<div class="field">
					<label class="glyphicon glyphicon-user"></label>
					<input placeholder="会员名" maxlength="20" id="login_username" type="text">
				</div>
				<div class="field">
					<label class="glyphicon glyphicon-lock"></label>
					<input placeholder="密码" maxlength="20" id="login_password" type="text">
				</div>
				<div class="field">
					<input placeholder="验证码" maxlength="6" id="captchacode_login" type="text" style="width:180px;">
					<img id="changeCaptcha_login" src="getCaptchaCode" onclick="changeCaptcha_login();">
				</div>
				<div class="mt-15">
					<button class="btn-submit" onclick="login();">登录</button>
				</div>
			</div>
			<div class="login-links mt-15">
				<span class="pull-left">
					<a href="regist" style="color:#e4b35a;text-decoration: none;">注册账号</a>
				</span>
				<span class="pull-right" style="height: 18px; line-height: 18px;">
					<input type="checkbox" id="remember_password" style="position: relative; top:-2px;">  
					<span style="position: relative; top:-3px;">记住密码</span>
				</span>
				<br>
				<br>
				<span class="pull-right">
					<a href="forgetpassword" style="color:#0973BE;text-decoration: none;">忘记密码</a>
				</span>
			</div>
		</div>
		
	</div>
	<%@include file="../common/foot.jsp" %>
</body>
</html>