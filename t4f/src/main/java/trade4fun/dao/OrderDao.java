package trade4fun.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import trade4fun.pojo.Order;

public interface OrderDao {
	
	int insert(Order record);
	
    Order selectById(Integer id);

    List<Order> selectByUidAndStat(@Param(value = "user_id") Integer uid, @Param(value = "stat") Integer stat);

    List<Order> selectByUidAndStatAndPage(@Param(value = "user_id") Integer uid, @Param(value = "stat") Integer stat,
                                          @Param(value = "startPos") Integer startPos, @Param(value = "size") Integer size);

    int getOrderNumsByUidAndStat(@Param(value = "user_id") Integer user_id, @Param(value = "stat") Integer stat);

    int update(Order record);

    int updateStatByDate(@Param(value = "oldstat") Integer oldstat, @Param(value = "newstat") Integer newstat, @Param(value = "date") Date date);
    
    int deleteByUidAndStat(@Param(value = "user_id") Integer uid, @Param(value = "stat") Integer stat);
}
