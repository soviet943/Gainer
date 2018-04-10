package trade4fun.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import trade4fun.dao.RedisDao;
import trade4fun.pojo.dto.GameDTO;
import trade4fun.service.OrderService;
import trade4fun.service.UserService;
import trade4fun.utils.RedisUtil;


/**
 * 定时任务
 */
@Component
public class Scheduler {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RedisUtil<GameDTO> redisUtil;
	@Autowired
	private RedisDao redisDao;
//1000分钟删一次没有验证的用户
    @Scheduled(fixedRate = 1000 * 60 * 1000)
    public void delNotValidateUser() {
        userService.delNotValidateUser();
    }
//10分钟检查一次过期的令牌
    @Scheduled(fixedRate = 1000 * 60 * 10)
    public void expireToken() {
        userService.expireToken();
    }
//1分钟检测未支付的订单（订单由未支付转为作废的时间为15分钟）
    @Scheduled(fixedRate = 1000 * 60 * 60)
    public void cancelOrder() {
        orderService.autoCancelOrder();
    }

//每30秒更新一次主页的饰品推荐（随机）和土豪推荐（价格大于1000）
    @Scheduled(fixedRate = 1000 * 30)
    public void update_random_items() {
		redisUtil.setObject("random_csgo", GameDTO.class, redisDao.select10csgo(null).toArray());
		redisUtil.setObject("random_dota2", GameDTO.class, redisDao.select10dota2(null).toArray());
		redisUtil.setObject("random_csgo_rich", GameDTO.class, redisDao.select10csgo(2000).toArray());
		redisUtil.setObject("random_dota2_rich", GameDTO.class, redisDao.select10dota2(2000).toArray());
    }
    
//每35秒根据最近的订单，更新饰品的update_time
    @Scheduled(fixedRate = 1000 * 35)
    public void update_items_updatetime() {
    	Date now = new Date();
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String update_time = dateFormat.format(now);
    	redisDao.update_csgo_updatetime(update_time, redisDao.select28id("csgo"));
    	redisDao.update_dota2_updatetime(update_time, redisDao.select28id("dota2"));
    }

}

