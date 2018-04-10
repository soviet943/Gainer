package trade4fun.service.impl;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import trade4fun.dao.CSGODao;
import trade4fun.dao.CartDao;
import trade4fun.dao.CodeDao;
import trade4fun.dao.DOTA2Dao;
import trade4fun.dao.OrderDao;
import trade4fun.dao.Order_ItemDao;
import trade4fun.dao.OrderitemDao;
import trade4fun.dao.UserDao;
import trade4fun.pojo.CSGOItem;
import trade4fun.pojo.Cart;
import trade4fun.pojo.Code;
import trade4fun.pojo.DOTA2Item;
import trade4fun.pojo.Order;
import trade4fun.pojo.Orderitem;
import trade4fun.pojo.User;
import trade4fun.service.OrderService;
import trade4fun.utils.PageUtil;
import trade4fun.utils.Result;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private Order_ItemDao order_itemDao;
	@Autowired
	private OrderitemDao orderitemDao;
	@Autowired
	private CodeDao codeDao;
	@Autowired
	private CSGODao csgoDao;
	@Autowired
	private DOTA2Dao dota2Dao;
	@Autowired
	private CartDao cartDao;
	@Autowired
	private UserDao userDao;

	//1 添加到购物车
	public Result addToCart(Integer user_id, String item) {
		if(cartDao.insert(user_id, item) > 0) {
			userDao.updateCartCount(cartDao.selectCartNum(user_id), user_id);
			return Result.success();
		}
		return Result.fail("添加至购物车失败");
	}
	
	//2 准备从购物车生成订单
	public Result goToBalance(Integer user_id) {
		//从购物车中得到所有饰品对应的id
		List<Cart> cart = cartDao.selectByUid(user_id);
		if (cart.size() == 0) {
			return Result.fail("购物车空空如也");
		}
		List<String> list = new ArrayList<String>();
		for(int i=0; i<cart.size(); i++) {
			list.add(cart.get(i).getGame_itemid());
		}
		balance(user_id, list);
		return Result.success();
	}
	
	//3 生成订单和订单下的订单明细
		public Result balance(int user_id, List<String> items) {
			//生成订单明细
			double total = 0;
			Order order = new Order();
			order.setUser_id(user_id);
			orderDao.insert(order);
			Integer orderid = order.getId();
			for (String item : items) {
				String game_type = item.substring(0, item.indexOf("_"));
				Integer item_id = Integer.parseInt(item.substring(item.indexOf("_")+1));
				Orderitem orderitem = new Orderitem();
				if(game_type.equals("csgo")) {
					CSGOItem csgoitem = csgoDao.selectById(item_id);
					total = total + csgoitem.getPrice();
					orderitem.setGame_type("csgo");
					orderitem.setItem_id(csgoitem.getId());
					orderitem.setItem_name(csgoitem.getItem_name());
					orderitem.setPrice(csgoitem.getPrice());
					orderitem.setItem_img("csitem/"+csgoitem.getImg());
					orderitemDao.insert(orderitem);
					Integer itemid = orderitem.getId();
					order_itemDao.insert(orderid, itemid);
				}
				else if(game_type.equals("dota2")) {
					DOTA2Item dota2item = dota2Dao.selectById(item_id);
					total = total + dota2item.getPrice();
					orderitem.setGame_type("dota2");
					orderitem.setItem_id(dota2item.getId());
					orderitem.setItem_name(dota2item.getItem_name()	);
					orderitem.setPrice(dota2item.getPrice());
					orderitem.setItem_img("dota2_item/"+dota2item.getImg());
					orderitemDao.insert(orderitem);
					Integer itemid = orderitem.getId();
					order_itemDao.insert(orderid, itemid);
				}
				else if(game_type.equals("other")) {
					
				}
				else {continue;}
			}
			DecimalFormat df = new DecimalFormat("0.00");
			order.setPrice(Double.parseDouble(df.format(total)));
			order.setStat(Order.STAT_NOT_PAY);
			orderDao.update(order);
			return Result.success(order);
		}

	public Result cancelOrder(int user_id, int orderid) {
		Order order = orderDao.selectById(orderid);
		//如果orderid错误或者用户与订单不匹配，就返回错误
		if(order == null || order.getUser_id() != user_id) {
			return Result.fail("订单不存在或用户与订单不匹配");
		}
		order.setStat(Order.STAT_CANCEL);
		if (1 == orderDao.update(order)) {
			return Result.success("订单状态更改成功");
		}
		return Result.fail("订单状态更改失败");
	}

	@Transactional
	public Result pay(int user_id, int orderid) {
		Order order = orderDao.selectById(orderid);
		//如果orderid错误或者用户与订单不匹配，就返回错误
		if(order == null || order.getUser_id() != user_id) {
			return Result.fail("订单不存在或用户与订单不匹配");
		}
		User user = userDao.selectById(user_id);
		Double left = user.getMoney();
		if(left < order.getPrice()) {
			return Result.fail("金额不足");
		}
		user.setMoney(left - order.getPrice());
		userDao.update(user);
		order.setStat(Order.STAT_PAY);
		orderDao.update(order);
		List<Integer> orderitems = order_itemDao.selectByOrder(orderid);
		List<Orderitem> orderitemList = orderitemDao.selectByIds(orderitems);
		for (Orderitem item : orderitemList) {
			//生产激活码并插入数据库
			String uuid = UUID.randomUUID().toString();
			Code code = new Code();
			code.setItem_id(item.getId());
			code.setGame_type(item.getGame_type());
			code.setUser_id(user_id);
			code.setCode(uuid);
			code.setStat(0);
			codeDao.insert(code);
			//插入code后更新orderitem的对应codeid
			item.setCode_id(code.getId());
			orderitemDao.update(item);
		}
		return Result.success();
	}

	public boolean exists(int orderid) {
		return orderDao.selectById(orderid) != null;
	}

	public Result getNotPayOrders(int user_id, int page) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageUtil pageUtil = new PageUtil(orderDao.getOrderNumsByUidAndStat(user_id, Order.STAT_NOT_PAY), page, 10);
        List<Order> orders = orderDao.selectByUidAndStatAndPage(user_id, Order.STAT_NOT_PAY, pageUtil.getStartPos(), 10);
        Map<Integer, Order> orderid_order = new HashMap<Integer, Order>();//订单id和对应的订单详情 映射对
        Map<Integer, List<Orderitem>> orderid_orderitems = new HashMap<Integer, List<Orderitem>>();//订单id和订单明细 映射对
        for(int i=0; i<orders.size(); i++){
        	List<Integer> orderitemids = order_itemDao.selectByOrder(orders.get(i).getId());//存放一张订单下的所有订单明细的id
        	List<Orderitem> orderitems = orderitemDao.selectByIds(orderitemids);//根据这些id得到订单下的所有订单明细
        	Integer orderid = orders.get(i).getId();
        	orderid_order.put(orderid, orders.get(i));
        	orderid_orderitems.put(orderid, orderitems);
        }
        map.put("orderid_order", orderid_order);
        map.put("orderid_orderitems", orderid_orderitems);
        map.put("page", pageUtil);
        return Result.success(map);
	}

	public Result getPaidOrders(int user_id, int page) {
		PageUtil pageUtil = new PageUtil(orderDao.getOrderNumsByUidAndStat(user_id, Order.STAT_PAY), page, 10);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("order", orderDao.selectByUidAndStatAndPage(user_id, Order.STAT_PAY, pageUtil.getStartPos(), 10));
        map.put("page", pageUtil);
        return Result.success(map);
	}

	public Result getCancelOrders(int user_id, int page) {
		PageUtil pageUtil = new PageUtil(orderDao.getOrderNumsByUidAndStat(user_id, Order.STAT_CANCEL), page, 10);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("order", orderDao.selectByUidAndStatAndPage(user_id, Order.STAT_CANCEL, pageUtil.getStartPos(), 10));
        map.put("page", pageUtil);
        return Result.success(map);
	}

	//超过7天自动清空购物车的内容
	public void autoDelCart() {
		Date date = new Date();
		date.setTime(date.getTime() - 1000 * 60 * 60 * 24 * 7);
		cartDao.autoDelete(date);
	}
	//超过7天自动将订单支付状态改为未支付状态
	public void autoCancelOrder() {
		Date date = new Date();
        date.setTime(date.getTime() - 1000 * 60 * 60 * 24 * 7);  // 过期时间为7天
        orderDao.updateStatByDate(Order.STAT_NOT_PAY, Order.STAT_CANCEL, date);
	}

}
