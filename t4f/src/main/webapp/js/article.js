$(function() {
	load_comments(1);
	var iflike = $("#iflike").attr("value");
	if(iflike == "true") {
		$(".zan-btn").addClass("liked");
	} else {
		$(".zan-btn").removeClass("liked");	
	}
})


//加载评论
function load_comments(page) {
	$("#comment-pagenation").children().remove();
	var _aid = parseInt($("#aid").attr("value"));
	var _size = parseInt($("#comment_num").attr("value"));
	$.post("discuss/getAllReply", {
		aid : _aid,
		current : page,
		size : _size
	}, function(result){
		var str = "";
		$.each(result.data.comments, function(index, item) {
			var time = time_convert(item.ctime);
			str+='<li id="li_'+item.cid+'">'
			str+=	'<a>'
			str+=		'<img class="img-circle-1 mt-10" src="https://i.c5game.com/avatar.png@50w.png">'
			str+=	'</a>'
			str+=	'<div class="right-msg">'
			str+=		'<span style="color:#4F74AC;">' + item.username + '</span>:  '
			str+=		item.content
			str+=	'</div>'
			str+=	'<div class="reply">' 	
			str+=		time
			str+=		'<a onclick="open_reply('+_aid+','+item.cid+',\''+item.username+'\',0'+','+item.uid+');" class="pull-right reply-reply">回复</a>'
			str+=		'<span class="pull-right iconfont" id="comment_vote_'+item.cid+'" onclick="comment_like('+item.cid+');" style="cursor: pointer;margin-right: 15px;">&#xe62e;'
			str+=			'<span class="voted_num">'+item.voted+'</span>'
			str+=		'</span>'
			str+=	'</div>'
				
			if(item.haschild > 3) {
				str+='<ul id="reply_ul_'+item.cid+'" class="comments-list2 clearfix">';
				$.each(item.reply_list.slice(0,3), function(index, reply_item) {
					var time1 = time_convert(reply_item.ctime);
					str+='<li class="clearfix" id="rid_'+reply_item.rid+'">'			
					str+=	'<div class="right-msg-reply">'
					str+=		'<a href="user/'+reply_item.uid+'">'
					str+=            '<span style="color:#4F74AC">' + item.username + '</span>:  '
		            str+=		'</a>'        
		            str+=        reply_item.content
		            str+=	'</div>'
	            	str+=	'<div class="mt-10 reply-bottom">'
        			str+=		time1
        			str+=		'<a onclick="open_reply('+_aid+','+item.cid+',\''+item.username+'\',1'+','+reply_item.uid+');" class="pull-right reply-reply-bottom">回复</a>'
        			str+=		'<span class="pull-right iconfont" id="reply_vote_'+reply_item.rid+'" onclick="reply_like('+reply_item.rid	+');" style="cursor: pointer;margin-right: 15px;">&#xe62e;'
        			str+=			'<span class="voted_num">'+reply_item.voted+'</span>'
        			str+=		'</span>'
        			str+=	'</div>'
				})
				var length = item.haschild;
				var remain = length - 3;
				str+='<a class="more-comment" onclick="load_more_reply('+item.aid+','+item.cid+','+length+')">更多'+remain+'条评论</a>'
				str+='</li></ul>'
			} else if(item.haschild > 0) {
				str+='<ul class="comments-list2 clearfix">';
				$.each(item.reply_list, function(index, reply_item) {
					var time1 = time_convert(reply_item.ctime);
					str+='<li class="clearfix" id="rid_'+reply_item.rid+'">'			
					str+=	'<div class="right-msg-reply">'
					str+=		'<a href="user/'+reply_item.uid+'">'
					str+=            '<span style="color:#4F74AC">' + reply_item.username + '</span>:  '
		            str+=		'</a>'        
		            str+=        reply_item.content
		            str+=	'</div>'
	            	str+=	'<div class="mt-10 reply-bottom">'
        			str+=		time1
        			str+=		'<a onclick="open_reply('+_aid+','+item.cid+',\''+reply_item.username+'\',1'+','+reply_item.uid+');" class="pull-right reply-reply-bottom">回复</a>'
        			str+=		'<span class="pull-right iconfont" id="reply_vote_'+reply_item.rid+'" onclick="reply_like('+reply_item.rid	+');" style="cursor: pointer;margin-right: 15px;">&#xe62e;'
        			str+=			'<span class="voted_num">'+reply_item.voted+'</span>'
        			str+=		'</span>'
        			str+=	'</div>'
				})
				str+='</ul></li>'
			} else {
				str+='</li>'
			}
			
		})
		$("#comments").html(str);
		var size = parseInt($("#comment_num").attr("value"));
		var pages = size % 15 == 0 ? size / 15 : Math.ceil(size/15);
		pagination("comment-pagenation", pages, page, "load_comments")
	})
}

//文章评论上传
function comment() {
	var _aid = $("#aid").attr("value");
	var _content = $("#content").val();
	if(_content.length < 1) {
		alert("输入内容不能为空");
		return;
	}
	$.post("discuss/add_comment", {
		aid: _aid,
		content: _content,
	}, function(result) {
		var comments = $("#comments").html();
		var str="";
		var username = $("#username").val();
		var uid = $("#uid").val();
		var content = $("#content").html();
		if(result.success) {
			window.location.reload();
		}
	})
}

//点击回复，展开回复框 1、文章id 2、评论id 3、评论者的名称  4、类型：回复层主0 & 回复层内其他人1
function open_reply(aid, cid, comment_name, type, uid) {
	$("#comments").children("li").children("#reply-show").remove();
	var str = '';
	str+='<form id="reply-show">'
	str+=	'<textarea class="comment-reply" placeholder="回复  '+comment_name+'" style="float: right"></textarea>'
	str+=	'<div class="mt-10 clearfix pb-10 reply-btn">'
	str+=		'<a type="submit" onclick="send_reply('+aid+','+cid+',\''+comment_name+'\','+type+','+uid+');" class="btn btn-green mt-10 pull-right reply-comment">回复</a>'
	str+=	'</div>'
	str+='</form>'
	$("#li_"+cid).append(str);
	$("html, body").animate({
		scrollTop: $("#reply-show").offset().top-300
	})
}
//发送回复框内的内容
function send_reply(_aid, _cid, comment_name, type, uid) {
	var _content = $(".comment-reply").val();
	if(type ==1) {
		_content = '回复 <a href="user/'+uid+'">'+comment_name+'</a> '+_content;
	}
	$.post("discuss/add_reply", {
		aid: _aid,
		cid: _cid,
		content: _content
	})
}
//展开更多评论
function load_more_reply(_aid, _cid, _size) {
	$(".more-comment").remove();
	$("#reply-show").remove();
	node = "#reply_ul_"+_cid;
	_lastPos = $(node).children("li").length - 1;
	$.post("discuss/more_reply", {
		cid: _cid,
		lastPos: _lastPos,
		length: _size
	}, function(result) {
		var str = "";
		$.each(result, function(index, reply_item) {
			var time1 = time_convert(reply_item.ctime);
			str+='<li class="clearfix" id="rid_'+reply_item.rid+'">'			
			str+=	'<div class="right-msg-reply">'
			str+=		'<a href="user/'+reply_item.uid+'">'
			str+=            '<span style="color:#4F74AC">' + reply_item.username + '</span>:  '
            str+=		'</a>'        
            str+=        reply_item.content
            str+=	'</div>'
        	str+=	'<div class="mt-10 reply-bottom">'
			str+=		time1
			str+=		'<a onclick="open_reply('+_aid+','+_cid+',\''+reply_item.username+'\',1'+','+reply_item.uid+');" class="pull-right reply-reply-bottom">回复</a>'
			str+=		'<span class="pull-right iconfont" id="reply_vote_'+reply_item.rid+'" onclick="reply_like('+reply_item.rid	+');" style="cursor: pointer;margin-right: 15px;">&#xe62e;'
			str+=			'<span class="voted_num">'+reply_item.voted+'</span>'
			str+=		'</span>'
			str+=	'</div>'
			str+='</li>';
		})
		$(node).append(str);
		var remain = _size - $(node).children("li").length;
		if(remain > 0) {
			$(node).append('<a class="more-comment" onclick="load_more_reply('+_aid+','+_cid+','+_size+')">更多'+remain+'条评论</a>');
		}
	})
}


//文章点赞
function like() {
	var aid = $("#aid").attr("value");
	$.post("discuss/vote", {
		type: "article",
		id: aid
	})
	var zan = $(".zan-btn").hasClass("liked");
	if(zan) {
		$(".zan-btn").removeClass("liked");
		$("#vote_num").text(parseInt($("#vote_num").text())-parseInt(1));
	} else if(!zan) {
		$(".zan-btn").addClass("liked");
		$("#vote_num").text(parseInt($("#vote_num").text())+parseInt(1));
	}
	$(".zan-btn").attr("disabled", true);
	setTimeout(function() {
		$(".zan-btn").attr("disabled", false);
	},2000)
	
}
//评论点赞
function comment_like(_cid) {
	$.post("discuss/vote", {
		type: "comment",
		id: _cid
	}, function(result) {
		var node = "#comment_vote_"+_cid;
		if(result == "disliked") {
			$(node).removeClass("liked");
			var temp = $(node).children(".voted_num").text();
			$(node).children(".voted_num").text(parseInt(temp)-parseInt(1));
		} else if(result == "liked") {
			$(node).addClass("liked");
			var temp = $(node).children(".voted_num").text();
			$(node).children(".voted_num").text(parseInt(temp)+parseInt(1));
		}
		$(node).attr("disabled", true);
		setTimeout(function() {
			$(node).attr("disabled", false);
		},2000)
	})
}
//回复点赞
function reply_like(_rid) {
	$.post("discuss/vote", {
		type: "reply",
		id: _rid
	}, function(result) {
		var node = "#reply_vote_"+_rid;
		if(result == "disliked") {
			$(node).removeClass("liked");
			var temp = $(node).children(".voted_num").text();
			$(node).children(".voted_num").text(parseInt(temp)-parseInt(1));
		} else if(result == "liked") {
			$(node).addClass("liked");
			var temp = $(node).children(".voted_num").text();
			$(node).children(".voted_num").text(parseInt(temp)+parseInt(1));
		}
		$(node).attr("disabled", true);
		setTimeout(function() {
			$(node).attr("disabled", false);
		},2000)
	})
}





function time_convert(timeMills) {
	var time;
	var minute;
	var date = new Date(timeMills);
	var interval = new Date().getTime - date.getTime();
	if(date.getMinutes().toString().length == 1) {
		minute = '0'+date.getMinutes();
	} else {
		minute = date.getMinutes();
	}
	if(interval < 60*1000) {
		time = "刚刚";
	} else if(interval < 60*1000*3600*24) {
		time = date.getHours()+":"+date.getMinutes();
	} else if(interval < 60*1000*3600*48) {
		time = "昨天" + date.getHours()+":"+date.getMinutes();
	} else if(new Date().getFullYear() == date.getFullYear()) {
		time = date.getMonth()+1 + "月" + date.getDate() + "日  " + date.getHours() + ":" + minute;
	} else {
		time = date.getFullYear() + "年" + date.getMonth()+1 + "月" + date.getDate() + "日" + date.getHours() + ":" + minute;
	}
	return time;
}

//饰品查询结果分页 < 1 2 3 4 5 6 >
//参数: 1、分页的标签id 2、总页数  3、当前页数  4、方法名称 (注：仅仅是方法名称字符串，不带括号的)
function pagination(id, pages, current, target) {//参数:要分页的标签id，总页数，当前页数，分页连接地址的方法，搜索的关键词
	$("#"+id).children().remove();
	var ul = document.getElementById(id);
	var last = current - 1;
	var next = current + 1;
	if (last < 1) {
		last = 1
	}
	if (next > pages) {
		next = pages
	}
	//首页
	var home = document.createElement("li");
	home.innerHTML = "<a onclick='" + target + "(1)'>首页</a>";
	ul.appendChild(home);
	//上一页
	var li_first = document.createElement("li");
	li_first.innerHTML = "<a onclick='" + target + "(" + last + ")'>上一页</a>";
		ul.appendChild(li_first);
		//中间的数字
		if (pages <= 6) {
			for (i = 1; i <= pages; i++) {
				var li = document.createElement("li");
				li.id = "li_" + i;
				li.innerHTML = "<a onclick='" + target + "(" + i + ")'>" + i + "</a>";
				ul.appendChild(li);
			}
		}
		else {
			if(current <=4) {//最左面的情况
				for (i = 1; i <= 6; i++) {
					var li = document.createElement("li");
					li.id = "li_" + i;
					li.innerHTML = "<a onclick='" + target + "(" + i + ")'>" + i + "</a>";
					ul.appendChild(li);
				}
			}
		else if (current >= pages-2){//最右面的情况
			for (i = pages-3; i <= pages; i++) {
				var li = document.createElement("li");
		        li.id = "li_" + i;
		        li.innerHTML = "<a onclick='" + target + "(" + i + ")'>" + i + "</a>";
		        ul.appendChild(li);
			}
		}
		else {//中间的情况
			for (i = parseInt(current) - parseInt(3); i <= parseInt(current) + parseInt(2); i++) {
				var li = document.createElement("li");
		        li.id = "li_" + i;
		        li.innerHTML = "<a onclick='" + target + "(" + i + ")'>" + i + "</a>";
		        ul.appendChild(li);
			}
		}
	}
	//下一页
	var li_last = document.createElement("li");
	li_last.innerHTML = "<a onclick='" + target + "(" + next + ")'>下一页</a>";
	ul.appendChild(li_last);
	//尾页
	var end = document.createElement("li");
	end.innerHTML = "<a onclick='" + target + "(" + pages + ")'>尾页</a>";
	ul.appendChild(end);
	//选中的页面标签高亮
	$("#li_" + current).addClass("active");
}