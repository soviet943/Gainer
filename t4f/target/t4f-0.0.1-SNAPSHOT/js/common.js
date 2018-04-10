var wait;
$(function(){
	wait = 0;
	$("#login_modal").bind("click", function(){
		$("#register_modal").modal('hide');
	})
	$("#register_modal").bind("click", function(){
		$("#login_modal").modal('hide');
	})
	//登录注册来回切换
	$("#to_login").bind("click", function(){
		$('.modal').map(function() {
		    $(this).modal('hide');
		});
		setTimeout(function(){
			$("#login_modal").modal();
		}, 350);
	})
	$("#to_register").bind("click", function(){
		$('.modal').map(function() {
		    $(this).modal('hide');
		});
		setTimeout(function(){
			$("#register_modal").modal();
		}, 350);
	})
})
	
//登录部分
function login() {
	var login_username = $("#login_username").val();
	var login_password = $("#login_password").val();
	var captchacode = $("#captchacode_login").val();
	var remember = $("#remember_password").is(':checked');
	if(login_username.length == 0) {
		$(".alert-err").text("用户名不能为空");
		return;
	}
	if(login_password.length == 0) {
		$(".alert-err").text("密码不能为空");
		return;
	}
	if(captchacode.length == 0) {
		$(".alert-err").text("验证码不能为空");
		return;
	}
	$.post("login", {
		username: login_username,
		password: login_password,
		remember: remember,
		captchacode: captchacode
	},
	function(result){
        if (result.success) {
        	if(result.data.referer==null){
        		window.location.href = "dota2";
        	}
        	else {
        		window.location.href = result.data.referer;
        	}
        }
        else {
        	$(".alert-err").text(result.msg);
        }
	})
}
//注册部分
var uid;
function regist() {
    var regist_username = $("#register_username").val();
    var regist_password = $("#register_password").val();
    var regist_re_password = $("#register_re_password").val();
    var regist_email = $("#register_email").val();
    var captchacode = $("#captchacode_regist").val();
    var agree_agreement = $("#agree_agreement").is(':checked');
	if(!agree_agreement) {
		location.reload(true);//不同意协议就刷新界面，直到他同意为止
	}
	if(regist_username.length == 0) {
		$(".alert-err").text("用户名不能为空");
		return;
	}
	if(regist_password.length == 0) {
		$(".alert-err").text("密码不能为空");
		return;
	}
	if(regist_password != regist_re_password) {
		$(".alert-err").text("密码不一致，请重新确认");
		return;
	}
	if(regist_email.length == 0) {
		$(".alert-err").text("邮箱不能为空");
		return;
	}
	if(captchacode.length == 0) {
		$(".alert-err").text("验证码不能为空");
		return;
	}
    $.post("register", {
    	regist_username: regist_username,
    	regist_password: regist_password, 
    	regist_email: regist_email,
    	captchacode: captchacode
    },
    function (result) {
        if (!result.success) {
        	$(".alert-err").text(result.msg);
        }
        else {
            alert("注册成功,跳转到验证界面");
            window.location.href = "user/to_validate";
        }
    });
};
//退出登录
function outlogin() {
    $.post("logout", function (result) {
            if (result.success) {
                location.reload("true");
            } else {
                alert(result.msg);
            }
        }
    )
}
//获取验证码图片 
function changeCaptcha_login() {
    $("#changeCaptcha_login").attr("src","getCaptchaCode?"+Math.random());
}
function changeCaptcha_regist() {
    $("#changeCaptcha_regist").attr("src","getCaptchaCode?"+Math.random());
}

//加入购物车
function addToCart(e) {
	var path = $("#item_img").attr("src");
	var top = $("#item_img").offset().top;
	var left = $("#item_img").offset().left;
	var float_img = '<img class="float_img" src="' + path + '" style="position:absolute; z-index: 999; width:310px; height:280px; top:' + top + 'px;left:' + left + 'px"; />';
	if (!$(".float_img").is(":animated")) { 
        $(".sale-item-bg").append(float_img);
        $(".float_img").animate({ "top": "10px", "left": "1230px", "height": "0", "width": "0" }, 500);
        if($(".CarNum").text() == "") {
    		$(".CarNum").text("1"); 
    	}
    	else {
    		var a = $(".CarNum").text();
    		var b = parseInt(a) + parseInt(1);
    		$(".CarNum").text(b); 
    	}
    }  
	$.post("order/addtocart",{item:e});
}