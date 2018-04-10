package trade4fun.service;

import java.util.List;
import java.util.Map;

import trade4fun.utils.Result;

public interface OrderService {
	Result balance(int user_id, List<String> items_map);//创建新订单
	Result addToCart(Integer user_id, String item);//添加至购物车
	Result goToBalance(Integer user_id);//从购物车准备结算
	Result cancelOrder(int user_id, int orderid);//取消订单
	Result pay(int user_id, int orderid);//支付订单
	boolean exists(int orderid);//判断订单是否存在
	Result getNotPayOrders(int user_id, int page);//获取待支付订单
	Result getPaidOrders(int user_id, int page);//获取已支付过的订单
	Result getCancelOrders(int user_id, int page);//获取取消的订单
	void autoCancelOrder();//自动去掉15分钟还未支付的订单，定时器每1分钟检查一次
}
