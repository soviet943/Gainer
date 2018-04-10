<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 模态框部分 -->
	<!-- 模态框 登录 -->
	<div class="modal fade" id="login_modal">
	    <div class="modal-dialog" style="width:500px;">
	        <div class="modal-content">
        		<button type="button" class="close login-close" data-toggle="modal" data-dismiss="modal">×</button>
	        	<div class="modal_login">
	        		<span style="color:white; font-size: 14px;">登录</span>
	        		<span class="alert-err" style="margin-left: 3px;color:#e46409; "></span>
	        		<span style="color:#e46409; font-size: 14px; float:right;" id="to_register">立即注册</span>
	        		<div class="field">
						<span class="glyphicon glyphicon-user"></span>
						<input placeholder="会员名" maxlength="20" id="login_username" type="text">
					</div>
					<div class="field">
						<span class="glyphicon glyphicon-lock"></span>
						<input placeholder="密码" maxlength="20" id="login_password" type="password">
					</div>
					<div class="field">
						<input placeholder="验证码" maxlength="6" id="captchacode_login" type="text" style="width:140px;">
						<img id="changeCaptcha_login" src="getCaptchaCode" onclick="changeCaptcha_login();" style="position: relative;top: -2px;left: -4px;">
					</div>
					<div style="margin-top: 10px;"></div>
	        		<label style="font-weight: normal" class="mt-2" for="remember_password">
	        			<input id="remember_password" type="checkbox" style="position: relative;top:2px;">
	        			记住密码
	        		</label>
	        		<a href="forgetpassword" style="margin-left:100px;margin-top: 20px;">忘记密码</a>
	        		<div class="submit">
	        			<button onclick="login();" class="btn-submit" data-loading-text="正在登录...">登　录</button>
	        		</div>
	        	</div>
	        </div>
	    </div>
	</div>
	<!-- 模态框 注册 -->
	<div class="modal fade" id="register_modal">
	    <div class="modal-dialog" style="width:500px;">
	        <div class="modal-content">
        		<button type="button" class="close login-close" data-toggle="modal" data-dismiss="modal">×</button>
	        	<div class="modal_register">
	        		<a style="color:white; font-size: 14px;">注册新用户</a>
	        		<span class="alert-err" style="margin-left: 3px;color:#e46409;"></span>
	        		<a style="color:#e46409; font-size: 14px; float:right;" id="to_login">已有账号 点击登录</a>
	        		<div class="field">
						<span class="glyphicon glyphicon-user"></span>
						<input class="login-text" placeholder="会员名" maxlength="20" id="register_username" type="text">
					</div>
					<div class="field">
						<span class="glyphicon glyphicon-lock"></span>
						<input class="login-text" placeholder="密码" maxlength="20" id="register_password" type="password">
					</div>
					<div class="field">
						<span class="glyphicon glyphicon-lock"></span>
						<input class="login-text" placeholder="重复密码" maxlength="20" id="register_re_password" type="password">
					</div>
					<div class="field">
						<span class="glyphicon glyphicon-envelope"></span>
						<input class="login-text" placeholder="邮箱" maxlength="20" id="register_email" type="text">
					</div>
	        		<div class="field">
						<input placeholder="验证码" maxlength="6" id="captchacode_regist" type="text" style="width:140px;">
						<img id="changeCaptcha_regist" src="getCaptchaCode" onclick="changeCaptcha_regist();" style="position: relative;top: -2px;left: -4px;">
					</div>
					<div style="margin-top: 10px;"></div>
	        		<label style="font-weight: normal" class="mt-2">
	        			<input id="agree_agreement" type="checkbox" checked="checked">
						<a href="agreement" style="text-decoration: none;">同意网站协议</a>
	        		</label>
	        		<div class="submit">
	        			<button onclick="regist();" class="btn-submit" data-loading-text="正在登录...">注   册</button>
	        		</div>
	        	</div>
	        </div>
	    </div>
	</div>
	<!-- 模态框 请充值 -->
	<div class="modal fade" id="need_money_modal">
	    <div class="modal-dialog" style="width:500px;">
	        <div class="modal-content" style="border:1px solid #576170;">
	        	<div class="modal-header" style="border-bottom:1px solid #576170;">
		        	<h5 id="myModalLabel" class="modal-title" style="display: inline-block;color:white;">您的账户余额不足，请充值</h5>
	        		<button type="button" class="close login-close" data-toggle="modal" data-dismiss="modal">×</button>
	        	</div>
	        	<div class="modal-body" style="background-color: #2c3040; margin:20px; margin-bottom: 30px;">
	        		<div class="text-center">
	        			<p style="margin-top: 10px;font-size: 14px;">
	        			 	账户还需要<span style="color:#e46409;">
	        			 	${item.data.price -money}</span>
							才能购买此件饰品
	        			</p>
	        			<a class="btn btn-success" style="margin-top: 30px;" href="user/payment?amount=${item.data.price -money}" target="_blank">立即充值</a>
	        		</div>
	        	</div>
	        </div>
	    </div>
	</div>
