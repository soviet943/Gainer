<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>"> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理登录</title>
<script src="js/jquery-2.0.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style type="text/css">

</style>
</head>
<body>
	<div class="login">
	    <h1>WePlay 后台管理</h1>
	    <input id="username" type="text" placeholder="用户名" required="required"/>
	    <input id="password" type="password" name="p" placeholder="密码" required="required"/>
	    <button onclick="login()" class="btn btn-primary btn-block btn-large">登录</button>
	</div>
</body>
<script type="text/javascript">
	function login() {
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		$.post("admin/login", {username:username, password:password}, function(result){
			if(result.success) {
				window.location.href = "admin/home"
			} else {
				alert(result.msg);
			}
		})
	}
</script>
</html>