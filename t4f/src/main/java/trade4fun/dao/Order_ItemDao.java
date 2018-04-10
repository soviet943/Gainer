package trade4fun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/*
 * 一对多映射表，即一个账单对应此账单中的多条明细
 */

public interface Order_ItemDao {
	
	int insert(@Param(value = "orderid") Integer orderid, @Param(value = "itemid") Integer itemid);

    List<Integer> selectByOrder(Integer orderid);//通过账单Id查找此账单下的所有明细
    
    int delete(Integer orderid);
    
}
