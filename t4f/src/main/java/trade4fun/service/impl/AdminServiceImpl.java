package trade4fun.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import trade4fun.dao.CSGODao;
import trade4fun.dao.UserDao;
import trade4fun.pojo.CSGOItem;
import trade4fun.pojo.User;
import trade4fun.service.AdminService;
import trade4fun.utils.MsgCenter;
import trade4fun.utils.PageUtil;
import trade4fun.utils.PasswordUtil;
import trade4fun.utils.RedisUtil;
import trade4fun.utils.Result;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private CSGODao csgodao;
	@Autowired
	private UserDao userdao;
	@Autowired
    private RedisUtil redisUtil;
	
//csgo部分
	public Result csgo_deleteByID(Integer id) {
		int result = csgodao.deleteByID(id);
		if (result == 0) return Result.fail("删除失败");
		return Result.success();
	}
	public Result csgo_insert(CSGOItem item) {
		int result = csgodao.insert(item);
		if (result == 0) return Result.fail("添加失败");
		return Result.success();
	}
	public Result csgo_update(CSGOItem item) {
		int result = csgodao.updateByID(item);
		if (result == 0) return Result.fail("更新失败");
		return Result.success();
	}
	
//用户部分
	public Result login(String username, String password) {
        if (username == null || password == null) {
            return Result.fail(MsgCenter.EMPTY_LOGIN);
        } else if (username.equals("admin") == false) {
            return Result.fail(MsgCenter.LOGIN_NOT_ALLOW);
        }
        User user = userdao.selectByName(username);
        // 用户名不存在或者密码错误或者用户已经被删除
        if (user == null || !user.getPassword().equals(PasswordUtil.pwd2Md5(password))
                || user.getStat().equals(User.STAT_DEL)) {
            return Result.fail(MsgCenter.ERROR_LOGIN);
        } else if (user.getStat().equals(User.STAT_RESTRICT)) {
            return Result.fail(MsgCenter.USER_RESTRICT);
        } else {
            String token = UUID.randomUUID().toString().replaceAll("-", "");
            redisUtil.putEx(token, String.valueOf(user.getId()), 60 * 60 * 24);
            return Result.success(token);
        }
    }
	public Result getUserByPage(int page) {
		PageUtil pageUtil = new PageUtil(userdao.selectByPage_N(), page);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", userdao.selectByPage(pageUtil.getStartPos(), pageUtil.getSize()));
        map.put("page", pageUtil);
        return Result.success(map);
	}
	public Result restrict_user(Integer uid) {
		User user = new User();
        user.setId(uid);
        user.setStat(User.STAT_RESTRICT);
        if (1 == userdao.update(user)) {
            return Result.success("操作成功");
        } else {
            return Result.fail("限制用户失败");
        }
	}
	public Result relieve_user(Integer uid) {
		User user = new User();
        user.setId(uid);
        user.setStat(User.STAT_OK);
        if (1 == userdao.update(user)) {
            return Result.success("操作成功");
        } else {
            return Result.fail("恢复用户失败");
        }
	}
	public Result delete_user(Integer uid) {
		User user = new User();
        user.setId(uid);
        user.setStat(User.STAT_DEL);
        if (1 == userdao.update(user)) {
            return Result.success("操作成功");
        } else {
            return Result.fail("删除用户失败");
        }
	}
	public Result getUserByName(String name) {
		return Result.success(userdao.selectByName(name));
	}
	public Result getUserByEmail(String email) {
		return Result.success(userdao.selectByEmail(email));
	}
	public Result getUserByStat(Integer stat) {
		return Result.success(userdao.selectBystat(stat));
	}
}
