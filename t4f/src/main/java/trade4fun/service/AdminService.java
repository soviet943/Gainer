package trade4fun.service;

import trade4fun.pojo.CSGOItem;
import trade4fun.utils.Result;

public interface AdminService {
	
	//csgo部分
	Result csgo_deleteByID(Integer id);
	Result csgo_insert(CSGOItem item);
	Result csgo_update(CSGOItem item);
	
	//用户管理部分
	Result login(String username, String password);//管理员登录
	Result getUserByPage(int page);//获取所有用户
	Result getUserByName(String name);
	Result getUserByEmail(String email);
	Result getUserByStat(Integer stat);
	Result restrict_user(Integer uid);//限制账户操作
	Result relieve_user(Integer uid);//解除账户限制
	Result delete_user(Integer uid);//删除用户
}
