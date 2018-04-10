var selected_card_id;
$(function(){
	$.post("order/cart", function(result){
		$.each(result.data.itemlist, function(i, each_item){
			var date = new Date(each_item.ctime);
			date = date.toLocaleString();
			var inner_content = 
				'<tr id="item_tr">'+
					'<input id="cart_id" type="hidden" name="cart_id" value="' + each_item.cart_id  + '">'+
					'<td style="padding-left: 10px;"  class="abc-checkbox abc-checkbox-info"><input class="ifselected" id="ifselected'+i+'" type="checkbox" checked="true"><label for="ifselected'+i+'"></label></td>'+
					'<td><a class="cartitem-img-bg"><img src="http://120.78.164.143:80/images/' + each_item.img + '.png"' + ' style="width: 96px;height: 64px;"></a></td>'+
					'<td id="item_name">' + each_item.item_name +'</td>'+
					'<td>' + each_item.game_type +'</td>'+
					'<td id="item_price">' + each_item.price +'</td>'+
					'<td>' + date +'</td>'+
					'<td style="padding-right: 10px;"><a class="btn btn-primary btn-sm" id="delete_cartitem">删除</a></td>'+
				'</tr>'
			$(inner_content).appendTo("#iteminfo");
		})
		count();
	})
	
	
	$("#iteminfo").delegate(".ifselected", "click", function(){
		count();
	})
	//弹出删除模态框
	$(".cart-table").delegate("#delete_cartitem", "click", function(){
		selected_card_id = $(this).parent().parent().children("#cart_id").attr("value");
		$("#delete_cartitem_modal").modal('show')
	})
	$(".cart").delegate("#delete_cartitems", "click", function(){
		if($("#item_tr").length != 0) {
			$("#delete_cartitems_modal").modal('show')
			$("#delete_Num").text($(".ifselected:checked").length)
		}
	})
	//选中/取消所有
	var selectAll_latch = true;
	$(".cart").delegate("#selectAll", "click", function(){
		if(selectAll_latch == true) {
			$(".ifselected").prop("checked", false);
			selectAll_latch = false;
		}
		else if(selectAll_latch == false) {
			$(".ifselected").prop("checked", true);
			selectAll_latch = true;
		}
		count();
	})
	//提交购物车(得到没有选中的购物车选项，并从cart表中删除这些选项，最后根据cart表的内容生成订单和订单明细
	$(".cart").delegate("#submit_cart", "click", function(){
		var _list = [];//存放选中的
		var j = 0;
		var length = $("#iteminfo").children("tr").length;
		for (i=0; i<length; i++) {
			var check = $("#iteminfo").children("#item_tr").eq(i).children("td").children("ifselected").prop("checked");
			if(!check) {
				var cart_id = $("#iteminfo").children("#item_tr").eq(i).children("#cart_id").attr("value");
				_list[j] = cart_id;
				j++;
			}
		}
		$.ajax({
			type:"POST",
			url:"order/balance",
			data:{ "ids": _list }, 
			traditional: true, 
			success: function(result) {
				location.reload();
			}
		})
	})
})

//删除购物车内的单个物品
function submit_delete_cartitem() {
	$("#delete_cartitem_modal").modal('hide')
	$.post("order/delete/cartitem", {id: selected_card_id}, function(result){
		if(result.success) {
			location.reload();
		}
	})
}
//删除多个
function submit_delete_cartitems(){
	var deleteitems = [];//存放选中的
	var j = 0;
	var length = $("#iteminfo").children("tr").length;
	for (i=0; i<length; i++) {
		var check = $("#iteminfo").children("#item_tr").eq(i).children("td").children("ifselected").prop("checked");
		if(!check) {
			var cart_id = $("#iteminfo").children("#item_tr").eq(i).children("#cart_id").attr("value");
			deleteitems[j] = cart_id;
			j++;
		}
	}
	$.ajax({
		type:"POST",
		url:"order/delete/cartitems",
		data:{ "ids": deleteitems}, 
		traditional: true, 
		success: function(result) {
			location.reload();
		}
	})
}

//计算总金额
function count() {
	var sum = 0;
	var number = 0;
	var length = $("#iteminfo").children("tr").length;
	for (i=0; i<length; i++) {
		var check = $("#iteminfo").children("#item_tr").eq(i).children("td").children(".ifselected").prop("checked");
		var money = $("#iteminfo").children("#item_tr").eq(i).children("#item_price").text();
		if(check) {
			number = number + 1;
			sum = parseFloat(sum) + parseFloat(money);
		}
	}
	$("#selectedNum").text(number);
	$("#total").text(sum.toFixed(2));
	if(sum == 0) {
		$("#submit_cart").addClass("disabled");
		$("#submit_cart").text("没有要买的哦")
	} else {
		$("#submit_cart").removeClass("disabled");
		$("#submit_cart").text("结算")
	}
}


