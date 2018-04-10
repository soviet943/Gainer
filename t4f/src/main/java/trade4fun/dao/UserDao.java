package trade4fun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import trade4fun.pojo.User;

public interface UserDao {
	
	User selectById(Integer id);
	
	User selectByName(String username);
	
	User selectByEmail(String email);
	
	int selectByPage_N();
	
	List<User> selectByPage(@Param(value = "startPos") Integer startPos, @Param(value = "size") Integer size);
	
	List<User> selectBystat(Integer stat);
	
	int insert(User user);
	
	int update(User record);
	
	int updateCartCount(@Param(value = "cartNum") Integer cartNum, @Param(value = "id") Integer id);
	
	int deleteById(Integer id);
	
}
