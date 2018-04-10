$(function() {
	$("#carousel").carousel({
		interval: 2000
	})
	
	$.ajax({
    	method: "POST",
    	url: 'discuss/select_filter',
    	success: function (result) {
    		if(result!=null && result.success==true) {
    			var str = "";
    			$.each(result.data, function(index, item){
    				var date = new Date(item.ctime);
    				var time = date.getMonth()+1 + "月" + date.getDate() + "日";
    				var game_type;
    				var article_type;
    				switch (item.article_type) 
    				{
    				case 0: game_type = ""; break;
    				case 1: game_type = "CSGO"; break;
    				case 2: game_type = "DOTA2"; break;
    				case 3: game_type = "绝地求生"; break;
    				case 4: game_type = "H1Z1"; break;
    				}
    				switch (item.game_type) 
    				{
    				case 1: article_type = "资讯"; break;
    				case 2: article_type = "饰品科普"; break;
    				}
    				str+='<div class="article-list">';
    				str+=	'<a href="discuss/article?aid=' + item.aid + '" target="_blank">';
    				str+=		'<img src="http://120.78.164.143/images/discuss/cover/' + item.cover + '">';
    				str+=	'</a>';
    				str+=	'<div class="article-breviary pull-left">';
    				str+=		'<a href="discuss/article?aid=' + item.aid + '" target="_blank" class="ft-white ft-16" style="color:#ddd">' + item.title + '</a>';
    				str+=		'<p class="main-page">' + item.brief + '</p>';
    				str+=		'<p class="mt-10">';
    				str+=			'作者:&nbsp;';
    				str+=			'<a target="_blank" href="">' + item.username + '&nbsp;&nbsp;&nbsp;&nbsp;</a>';
    				str+=			'<span>' + time + '&nbsp;&nbsp;&nbsp;&nbsp;</span>';
    				str+=			'<span class="icon iconfont">&#xe638;</span>';
    				str+=			'<a target="_blank">' + game_type + article_type + '</a>';
    				str+=			'<span class="pull-right">';
    				str+=				'<span style="display: inline-block;margin-left: 10px;">';
    				str+=					'<span class="icon iconfont">&#xe62b;&nbsp;</span>';
    				str+=					item.clicked;
    				str+=				'</span>';
    				str+=				'<span style="display: inline-block;margin-left: 10px;">';
    				str+=					'<span class="icon iconfont">&#xe626;&nbsp;</span>';
    				str+=					item.haschild;
    				str+=				'</span>';
    				str+=			'</span>';
    				str+=		'</p>';
    				str+=	'</div>';
    				str+='</div>';
    			})
    			$(".tab-content").html(str);
    		}
    	}
    })
	
})