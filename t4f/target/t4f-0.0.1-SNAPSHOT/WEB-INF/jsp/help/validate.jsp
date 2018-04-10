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
<title>T4F 饰品商城 | 发送邮箱激活码</title>
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css"> 
<link rel="stylesheet" href="css/head.css" type="text/css">
<script src="js/jquery-2.0.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<style type="text/css">
.container {
	background: #1E1E29;
	margin-top: 180px;
	height: 300px;
	width: 500px;
	font-size: 16px;
	border: 1px solid #E4B35A;
}	
.mt-10 {
	margin-top: 10px;
}
.mt-50 {
	margin-top: 50px;
}
</style>
</head>
<body>
	<%@include file="../common/head.jsp" %>
	<div class="container text-center">
		<div class="mt-50">尊敬的  <span style="color:#E4B35A;">${username } </span> , 欢迎您注册t4f , 您还需最后一步邮箱验证</div>
		<div class="mt-10">我们已将激活链接发送到   <span style="color:#E4B35A;">${mail } </span></div>
		<div class="mt-10">请您在邮箱中完成激活  , 一起加入t4f大家庭</div>
		<button id="send" onclick="sendMail();" class="mt-50 btn btn-primary pull-right"></button>
	</div>
	<div style="margin-bottom: 95px;"></div>
	<%@include file="../common/foot.jsp" %>
	<%@include file="../common/modal.jsp" %>
	
<script type="text/javascript">
var cur_time;
var storage;
$(function () {
	storage=window.localStorage;
	cur_second();
	if(storage.getItem("unlock_time")==null) {//如果是第一打开此界面，则设置localStorage自动发送邮件
		storage.setItem("unlock_time",cur_time);
		sendMail();
		time();
	}
	else if(storage.getItem("unlock_time") < cur_time) {//如果已经超过60s，而且是重新访问此界面，则自动发送邮箱
		storage.setItem("unlock_time",cur_time);
		sendMail();
		time();
	}
	else {
		time();//没超过60s，防止频繁发送
	}
})
function time() {
    if (cur_time >= storage.getItem("unlock_time")) {
        $("#send").removeAttr("disabled");
        $("#send").text("发送验证邮件");
    } else {
    	$("#send").attr("disabled", true);
    	$("#send").text((storage.getItem("unlock_time") - cur_time) +"s后重新发送");
    	cur_second();
        schedule();
    }
}
function cur_second() {
	var date = new Date();
	cur_time = Math.floor(date.getTime()/1000);
}
function schedule() {
    setTimeout(function () {
        time()
    }, 1000)
}

function sendMail() {
    $.post("user/sendMail", function (result) {
        if (result.success) {
        	cur_second();
        	storage.setItem("unlock_time", cur_time+60);
        	time();
        } else {
            alert(result.msg);
            storage.setItem("unlock_time", cur_time+60);
        	time();
        }
    });
}

</script>
</body>
</html>