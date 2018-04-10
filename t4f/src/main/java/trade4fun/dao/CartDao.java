package trade4fun.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import trade4fun.pojo.Cart;

public interface CartDao {
	
	List<Cart> selectByUid(@Param(value = "user_id") Integer user_id);
	
	Integer selectCartNum(@Param(value = "user_id") Integer user_id);
	
	Integer insert(@Param(value = "user_id") Integer user_id, @Param(value = "game_itemid") String game_itemid);
	
	Integer deleteByIds(@Param(value = "ids") List<Integer> ids);
	
	Integer deleteById(@Param(value = "id") Integer id);
	
	Integer deleteByUid(Integer user_id);
	
	Integer autoDelete(Date date);
	
}
