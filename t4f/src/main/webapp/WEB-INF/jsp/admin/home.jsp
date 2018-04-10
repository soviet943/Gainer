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
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"> 
<link href="css/admin.css" rel="stylesheet" type="text/css">
<script src="js/jquery-2.0.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/admin/admin.js"></script>
<title>管理员主页</title>
</head>
<body>
<!-- 标头状态栏^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^- -->
	<div class="head">
		<div class="state0" id="state0">
			<div class="form-search">
				<span class="glyphicon glyphicon-search" onclick="to_search()" style="color: #e4b35a;padding-left:5px;font-size: 15px;" onclick=""></span>
				<input name="search" type="text" id="csgo_search" style="font-weight: bold;">
			</div>
		</div>
	</div>
	
	<div id="select_type">
		<span id="type" class="type">
			<img src="images/icon/csgo.png">
			<span>CS:GO</span>
			<span style="padding-right: 5px;">▼</span>
		</span>
		<ul id="js-search-drop" class="isdrop">
			<li>
				<img src="images/icon/csgo.png">
				<span>CS:GO</span>
			</li>
			<li>
				<img src="images/icon/dota2.png">
				<span>DOTA2</span>
			</li>
			<li>
				<img src="images/icon/PUBG.png">
				<span>绝地求生</span>
			</li>
			<li>
				<img src="images/icon/h1z1.png">
				<span>H1Z1</span>
			</li>
			<li>
				<img src="images/icon/KF2.png">
				<span>KF2</span>
			</li>
			<li>
				<img src="images/icon/PD2.png">
				<span>PD2</span>
			</li>
			<li>
				<img src="images/icon/rust2.png">
				<span>Rust</span>
			</li>
			<li>
				<img src="images/icon/unturned.png">
				<span>unturned</span>
			</li>
			<li>
				<img src="images/icon/steam.png">
				<span>steam卡牌</span>
			</li>
		</ul>
	</div>
<!-- 菜单部分^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^- -->
	<div class="Menu">
		<div class="display">
			<img src="images/pic/logo_admin.png" id="logo" style="padding-top: 30px;"/>
			<img src="" id="csgodisplay" />
		</div>
		<a id="btn1"  class="list-group-item"><img src="images/game_icon/CSGO.jpg"> csgo饰品管理</a>
		<div class="toggle">
			<a id="csgo_select" onclick="load_csgo_search('', 1)" class="list-group-item" style="display: none;"><span class="glyphicon glyphicon-search">&nbsp;</span>查询</a>
			<a id="csgo_add" onclick="load_csgo_add()" class="list-group-item" style="display: none;"><span class="glyphicon glyphicon-plus">&nbsp;</span>增加</a>
		</div>
		<a id="btn2" class="list-group-item"><img src="images/game_icon/dota2.jpg"> dota2</a>
		<div class="toggle">
			<a id="dota2_select" class="list-group-item" style="display: none;"><span class="glyphicon glyphicon-search">&nbsp;</span>查询</a>
			<a id="dota2_add" class="list-group-item" style="display: none;"><span class="glyphicon glyphicon-plus">&nbsp;</span>增加</a>
		</div>
		<a id="btn3" class="list-group-item"><img src="images/game_icon/PUBG.jpg"> 绝地求生</a>
		<div class="toggle">
			<a id="pubg_select" class="list-group-item" style="display: none;"><span class="glyphicon glyphicon-search">&nbsp;</span>查询</a>
			<a id="pubg_add" class="list-group-item" style="display: none;"><span class="glyphicon glyphicon-plus">&nbsp;</span>增加</a>
		</div>
		<a id="btn4" class="list-group-item"><img src="images/game_icon/h1z1.jpg"> H1Z1</a>
		<div class="toggle">
			<a id="h1z1_select" class="list-group-item" style="display: none;"><span class="glyphicon glyphicon-search">&nbsp;</span>查询</a>
			<a id="h1z1_add" class="list-group-item" style="display: none;"><span class="glyphicon glyphicon-plus">&nbsp;</span>增加</a>
		</div>
		<a id="btn5" class="list-group-item"><img src="images/game_icon/军团要塞2.jpg"> 军团要塞2</a>
		<div class="toggle">
			<a id="tf_select" class="list-group-item" style="display: none;"><span class="glyphicon glyphicon-search">&nbsp;</span>查询</a>
			<a id="tf_add" class="list-group-item" style="display: none;"><span class="glyphicon glyphicon-plus">&nbsp;</span>增加</a>
		</div>
		<a id="btn6" class="list-group-item"><img src="images/game_icon/RUST.jpg"> RUST</a>
		<div class="toggle">
			<a id="rust_select" class="list-group-item" style="display: none;"><span class="glyphicon glyphicon-search">&nbsp;</span>查询</a>
			<a id="rust_add" class="list-group-item" style="display: none;"><span class="glyphicon glyphicon-plus">&nbsp;</span>增加</a>
		</div>
		<a id="btn7" class="list-group-item"><img src="images/game_icon/收获日2.jpg"> 收获日2</a>
		<div class="toggle">
			<a id="pd2_select" class="list-group-item" style="display: none;"><span class="glyphicon glyphicon-search">&nbsp;</span>查询</a>
			<a id="pd2_add" class="list-group-item" style="display: none;"><span class="glyphicon glyphicon-plus">&nbsp;</span>增加</a>
		</div>
		<a id="btn8" class="list-group-item"><img src="images/game_icon/KF2.jpg"> KF2</a>
		<div class="toggle">
			<a id="kf2_select" class="list-group-item" style="display: none;"><span class="glyphicon glyphicon-search">&nbsp;</span>查询</a>
			<a id="kf2_add" class="list-group-item" style="display: none;"><span class="glyphicon glyphicon-plus">&nbsp;</span>增加</a>
		</div>
		<a id="btn9" class="list-group-item"><img src="images/game_icon/unturned.jpg"> unturned</a>
		<div class="toggle">
			<a id="unturned_select" class="list-group-item" style="display: none;"><span class="glyphicon glyphicon-search">&nbsp;</span>查询</a>
			<a id="unturned_add" class="list-group-item" style="display: none;"><span class="glyphicon glyphicon-plus">&nbsp;</span>增加</a>
		</div>
		<a id="btn10" class="list-group-item"><img src="images/game_icon/steam卡片.jpg"> steam卡片</a>
		<div class="toggle">
			<a id="steam_select" class="list-group-item" style="display: none;"><span class="glyphicon glyphicon-search">&nbsp;</span>查询</a>
			<a id="steam_add" class="list-group-item" style="display: none;"><span class="glyphicon glyphicon-plus">&nbsp;</span>增加</a>
		</div>
		
		<div style="height:2px; background: #e4b35a;"></div>
		
		<a id="btn11" class="list-group-item">用户管理</a>
		<div class="toggle">
			<a id="user_select" onclick="load_user_select()" class="list-group-item" style="display: none;"><span class="glyphicon glyphicon-search">&nbsp;</span>用户查询</a>
			<a id="user_add" onclick="load_user_add()" class="list-group-item" style="display: none;"><span class="glyphicon glyphicon-plus">&nbsp;</span>添加用户</a>
		</div>
		<a id="btn12" class="list-group-item">交易记录</a>
		<div class="toggle">
			<a id="user_select" class="list-group-item" style="display: none;"><span class="glyphicon glyphicon-search">&nbsp;</span>账单查询</a>
		</div>
	</div>
<!-- 右侧内容部分^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^- -->	
   	<%@include file="csgo.jsp" %>
    	
   	<%@include file="user.jsp" %>

	
</body>
</html>