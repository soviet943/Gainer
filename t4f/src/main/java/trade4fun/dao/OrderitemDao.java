package trade4fun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import trade4fun.pojo.Orderitem;


public interface OrderitemDao {

	int insert(Orderitem orderitem);
	
    List<Orderitem> selectByIds(@Param(value = "ids") List<Integer> ids);

    int update(Orderitem record);
    
    int delete(@Param(value = "ids") List<Integer> ids);
    
}
