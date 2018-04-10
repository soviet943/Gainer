$(function(){
	$.get("order/notpay/1", function(result){
		$.each(result.data.orderid_orderitems, function(order_key, each_order){
			var order_ctime = new Date(result.data.orderid_order[order_key].ctime).toLocaleString();
			var order_table = 
				'<div class="order-table" id="order-' + order_key + '">'+
					'<div class="order-header">'+
						'<span id="order_time">' + order_ctime + '</span>'+
						'<span id="order_num">订单号' + order_key + '</span>'+
						'<span id="order_delete" class="pull-right glyphicon glyphicon-trash onclick=delete_order(' + result.data.orderid_order[order_key].id + ');"></span>'+
						'<span id="order_sum" class="pull-right">总额  ' + result.data.orderid_order[order_key].price + ' 元</span>'+
					'</div>'+
				'</div>';
			$(order_table).appendTo(".order-tables");
			$('<table class="order-body table col-md-8" id="order-body-' + order_key + '"></table>').appendTo("#order-"+order_key);
			$.each(each_order, function(j, each_item){
				var orderitem_ctime = new Date(each_item.ctime).toLocaleString();
				var orderitem = 
					'<tr id="item_tr">'+
						'<td style="padding-left:20px;"><a class="cartitem-img-bg"><img src="http://120.78.164.143:8080/images/' + each_item.item_img + '.png"' + ' style="width: 96px;height: 64px;"></a></td>'+
						'<td id="item_name">' + each_item.item_name +'</td>'+
						'<td id="game_type">' + each_item.game_type +'</td>'+
						'<td id="item_price">' + each_item.price +'</td>'+
						'<td>' + orderitem_ctime +'</td>'+
						'<td style="padding-right: 10px;"><a href="' + each_item.game_type + "/item/" + each_item.item_id + '" class="btn btn-primary btn-sm" id="assess_cartitem">评价</a></td>'+
					'</tr>';
				$(orderitem).appendTo("#order-body-"+order_key);
			})
		})
	})
	
})