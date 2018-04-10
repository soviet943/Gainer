<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%> 
<html>
<head>
<base href="<%=basePath%>"> 
<link href="images/pic/csgo.png" rel="shortcut icon">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>T4F 饰品商城 | 投稿</title>
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css"> 
<link rel="stylesheet" href="css/head.css" type="text/css">
<link rel="stylesheet" href="css/index.css" type="text/css">
<link rel="stylesheet" href="css/wangEditor.css" type="text/css">
<link rel="stylesheet" href="css/discuss.css" type="text/css">
<link rel="stylesheet" href="css/cropper.css" type="text/css">
<script src="js/jquery-2.0.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/wangEditor.js"></script>
<script src="js/discuss.js"></script>
<script src="js/cropper.js"></script>
</head>
<body>
<%@include file="../common/head.jsp" %>
<div class="container">
	<div class="detail-left">
		<div class="floor-nav floor-nav-title">
			投稿编辑
		</div>
		<div class="contribute">
			<div style="padding: 20px 20px 0px 20px;background: #1F2431;">
				<p>投稿规则：</p>
				<p>1、有图有内涵，欢迎来搞</p>
				<p>2、我们将审核文章择优录取</p>
				<div style="height: 10px;border-bottom: 1px solid rgba(27, 31, 42, 1);"></div>
			</div>
			<form id="wiki-form" action="" method="post" target="_blank">
				<div class="wiki-floor">
					<div class="clearfix">
						<div class="pull-left text-right mr-10">
							投稿栏目 ：
                        </div>
                        <div class="pull-left">
                        	<select id="select_game" name="appid" class="hero-select pull-right bottom-2" style="line-height: 28px">
                                <option value="1">CSGO</option>
                                <option value="2">DOTA2</option>
                                <option value="3">绝地求生</option>
                                <option value="4">H1Z1</option>
                                <option value="0">其它</option>
                            </select>
                        </div>
                        <div class="pull-left">
                        	<select id="select_type" name="category" class="hero-select pull-right bottom-2" style="line-height: 28px">
                                <option value="1">资讯</option>
                                <option value="2">饰品维基</option>
                            </select>
                        </div>
					</div>
				</div>
				
				<div class="wiki-floor pd-20" style="background: #1F2431;">
					<!-- 标题 -->
					<div class="clearfix mt-10">
                        <div class="pull-left text-right mr-10" style="width: 65px;line-height:30px">标题：</div>
                        <div class="pull-left">
                            <input id="input_title" style="width:650px;height: 30px; border:1px solid #404661;background: #272C3B;padding: 0 10px" maxlength="30" value="" placeholder="请输入文章标题，30字以内">
                        </div>
                    </div>
                    <!-- 简介 -->
                    <div class="clearfix mt-10">
                        <div class="pull-left text-right mr-10" style="width: 65px;line-height:60px">简介：</div>
                        <div class="pull-left">
                            <textarea id="input_abstract" style="width:650px;height: 60px; border:1px solid #404661;background: #272C3B;padding: 9px 10px;resize: none" maxlength="70" placeholder="请输入文章简介，40-70字"></textarea>
                        </div>
                    </div>
                    <!-- 文本编辑 -->
                    <div class="clearfix mt-10">
                    	<div class="pull-left text-right mr-10" style="width: 65px;line-height:30px">文本编辑：</div>
                    	<div class="pull-left">
                    		<!-- wangEditor编辑器的引用 -->
                    		<div class="wangEditor-container">
                    			<div id="editor">
                    			
                    			</div>
                    		</div>
                    	</div>
                    </div>
                    <!-- 设置封面 -->
                    <div class="clearfix mt-10" id="set_cover">
                    	<div class="pull-left text-right mr-10" style="width: 65px;line-height:30px">设置封面：</div>
                    	<div class="pull-left" style="width: 655px;">
                    		<span class="glyphicon glyphicon-plus" id="addPic"></span>
                      		<input style="float: left;display: none;" name="fileUp" id="fileUp" type="file"/>
                      		<span style="display: inline-block;margin-top: 30px;margin-left: 10px;">支持.gif,png,jpg,jpeg,bmp格式，推荐比例为200*120，5MB以内</span>
                        </div>
                    </div>
				</div>
				
				<div class="clearfix mt-10">
					<div class="pull-right">
						<span class="btn btn-blue" id="preview-btn">预览</span>
						<span class="btn btn-green" id="submit-btn">发布</span>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<div class="detail-right">
		<div class="user-logined">
			<div class="region-head">
				<img class="img-circle" src="https://i.c5game.com/avatar.png@100w.png" id="user-pic">
				<div class="text-center mt-5" style="line-height: 25px;">
					<span class="name-ellipsis-160">${user }</span>
				</div>
			</div>
			<div class="text-center mt-60">
				<a target="_blank" href="discuss/myarticle" class="btn btn-blue" style="height: 34px;border-radius: 0;line-height:32px;width: 115px;margin-right: 16px;">我的文章</a>
				<a target="_blank" href="discuss/submission" class="btn btn-green" style="height: 34px;border-radius: 0;line-height:32px;width: 115px;">点击投稿</a>
			</div>
		</div>
	</div>
	
</div>

<%@include file="../common/foot.jsp" %>
<%@include file="../common/modal.jsp" %>
<!-- 封面图片裁剪模态框 -->
<div class="modal fade in" id="cutimg" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
	    <div class="modal-content">
	        <div class="pd-5">
	        	<!-- 引用cropper裁剪 -->
               	<div class="cut-container">
               	</div>
	        </div>
	        <div class="modal-footer">
	            <button type="button" class="cut-submit btn btn-green">确定</button>
	            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        </div>
	    </div>
    </div>
</div>
<!-- 封面图片放大 -->
<div class="modal" id="img_zoom_in">
	<div class="modal-dialog">
		<div class="modal-content" id="zoom-content">
			<div class="display_zoom_in_img">
				<span class="glyphicon glyphicon-remove" id="zoom-close"></span>
				<img id="test_img" src="">
			</div>
		</div>
	</div>
</div>
<!-- 编辑预览 -->
<div class="modal" id="editor_preview">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="wiki-article">
				<div class="wiki-title">
					<span class="glyphicon glyphicon-remove font-22" id="preview_close"></span>
					<p class="ft-22" id="preview_title"></p>
					<p>
						来源：T4F平台&nbsp;&nbsp;&nbsp;作者：${user}&nbsp;&nbsp;&nbsp;刚刚 
						<span style="margin-left: 10px;"></span>                   
						<span class="iconfont">&#xe609;</span>
						10
					</p>
				</div>
				<div class="wiki-body">
				
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>