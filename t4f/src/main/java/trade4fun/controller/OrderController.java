package trade4fun.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import trade4fun.dao.CSGODao;
import trade4fun.dao.CartDao;
import trade4fun.dao.DOTA2Dao;
import trade4fun.dao.OrderDao;
import trade4fun.dao.Order_ItemDao;
import trade4fun.dao.OrderitemDao;
import trade4fun.dao.UserDao;
import trade4fun.exception.NotFoundException;
import trade4fun.pojo.CSGOItem;
import trade4fun.pojo.Cart;
import trade4fun.pojo.CartItem;
import trade4fun.pojo.DOTA2Item;
import trade4fun.pojo.Order;
import trade4fun.pojo.Orderitem;
import trade4fun.service.OrderService;
import trade4fun.utils.Result;
import trade4fun.utils.UserHolder;

@Controller
@RequestMapping(value = "order")
public class OrderController extends AbstractController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserHolder userHolder;
    @Autowired
    private CartDao cartDao;
    @Autowired
	private CSGODao csgoDao;
	@Autowired
	private DOTA2Dao dota2Dao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private Order_ItemDao order_ItemDao;
	@Autowired
	private OrderitemDao orderitemDao;
	@Autowired
	private UserDao userDao;
    //加入购物车
    @PostMapping(value = "addtocart")
    @ResponseBody
    public Result putcart(@RequestParam(value = "item") String item) {
    	return orderService.addToCart(userHolder.getUser().getId(), item);
    }
    //生成订单并删除购物车已选的项目
    @PostMapping(value = "balance")
    @ResponseBody
    public Result balance(@RequestParam(value = "ids") List<Integer> ids) {
    	Result result = orderService.goToBalance(userHolder.getUser().getId());
    	cartDao.deleteByIds(ids);
    	Integer user_id = userHolder.getUser().getId();
    	userDao.updateCartCount(cartDao.selectCartNum(user_id), user_id);//更新购物车数量
    	return result;
    }
    
    //购物车界面
    @GetMapping(value = "cart")
    public String tocart() {
    	return "order/cart";
    }
    //我的订单（全部订单，已付款）
    @GetMapping(value = "myorder")
    public String myorder() {
    	return "order/myorder";
    }
    
    //购物车界面
    @PostMapping(value = "cart")
    @ResponseBody
    public Result cart() {
    	List<Cart> cart = cartDao.selectByUid(userHolder.getUser().getId());
    	List<CartItem> cartitemlist = new ArrayList<CartItem>();
    	Double sum = new Double(0);
    	for(int i=0; i<cart.size(); i++) {
    		CartItem cartitem = new CartItem();
    		Cart c = cart.get(i);
    		String item = c.getGame_itemid();
    		String game_type = item.substring(0, item.indexOf("_"));
			Integer item_id = Integer.parseInt(item.substring(item.indexOf("_")+1));
    		if(game_type.equals("csgo")) {
				CSGOItem csgoitem = csgoDao.selectById(item_id);
				cartitem.setGame_type("csgo");
				cartitem.setCart_id(cart.get(i).getId());
				cartitem.setItem_name(csgoitem.getItem_name());
				cartitem.setPrice(csgoitem.getPrice());
				cartitem.setImg("csitem/"+csgoitem.getImg());
				cartitem.setCtime(cart.get(i).getCtime());
				cartitem.setUtime(cart.get(i).getUtime());
			}
			else if(game_type.equals("dota2")) {
				DOTA2Item dota2item = dota2Dao.selectById(item_id);
				cartitem.setGame_type("dota2");
				cartitem.setCart_id(cart.get(i).getId());
				cartitem.setItem_name(dota2item.getItem_name());
				cartitem.setPrice(dota2item.getPrice());
				cartitem.setImg("dota2_item/"+dota2item.getImg());
				cartitem.setCtime(cart.get(i).getCtime());
				cartitem.setUtime(cart.get(i).getUtime());
			}
			else if(game_type.equals("other")) {
				
			}
			else {continue;}
    		cartitemlist.add(cartitem);
    	}
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("itemlist", cartitemlist);
    	return Result.success(map);
    }
    
    //购物车项目删除单项
    @PostMapping(value = "delete/cartitem")
    @ResponseBody
    public Result delete_cartitem(@RequestParam(value = "id") Integer id) {
    	int a = cartDao.deleteById(id);
    	int b = 1;
    	if(a == b) {
    		return Result.success();
    	}
    	else return Result.fail("删除失败");
    }
    
  //购物车项目删除多项
    @PostMapping(value = "delete/cartitems")
    @ResponseBody
    public Result delete_cartitems(@RequestParam(value = "ids") List<Integer> ids) {
    	int a = cartDao.deleteByIds(ids);
    	int b = 1;
    	if(a == b) {
    		return Result.success();
    	}
    	else return Result.fail("删除失败");
    }
    
    //生成订单（未付款）
    @GetMapping(value = "generate/{orderid}")
    public String pay(@PathVariable(value = "orderid") Integer orderid, Model model) {
        if (!orderService.exists(orderid)) {
            throw new NotFoundException();
        }
        Order order = orderDao.selectById(orderid);
        List<Integer> orderitem_id_list = order_ItemDao.selectByOrder(orderid);//得到所有订单明细的id
        List<Orderitem> orderitemlist = orderitemDao.selectByIds(orderitem_id_list);//得到所有订单明细
        model.addAttribute("order", order);
        model.addAttribute("orderitemlist", orderitemlist);
        return "order/myorder";
    }
    
    
    //为订单付款并添加激活码（已付款）
    @PostMapping(value = "pay/{orderid}")
    @ResponseBody
    public Result payOrder(@PathVariable(value = "orderid") Integer orderid) {
        return orderService.pay(userHolder.getUser().getId(), orderid);
    }
    //取消订单
    @PostMapping(value = "cancel/{orderid}")
    @ResponseBody
    public Result cancelOrder(@PathVariable(value = "orderid") Integer orderid) {
        return orderService.cancelOrder(userHolder.getUser().getId(), orderid);
    }
    
    @GetMapping(value = "notpay/{page}")//未结算的订单
    @ResponseBody
    public Result getNotPayOrders(@PathVariable(value = "page") Integer page) {
        return orderService.getNotPayOrders(userHolder.getUser().getId(), page);
    }
    @PostMapping(value = "paid/{page}")//已结算的订单
    @ResponseBody
    public Result getPaidOrders(@PathVariable(value = "page") Integer page) {
        return orderService.getPaidOrders(userHolder.getUser().getId(), page);
    }
    @PostMapping(value = "cancel/{page}")//已取消的订单
    @ResponseBody
    public Result getCancelOrders(@PathVariable(value = "page") Integer page) {
        return orderService.getCancelOrders(userHolder.getUser().getId(), page);
    }

}
