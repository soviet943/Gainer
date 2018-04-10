package trade4fun.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import trade4fun.dao.TokenDao;
import trade4fun.dao.UserDao;
import trade4fun.event.EventModel;
import trade4fun.event.EventProducer;
import trade4fun.event.EventType;
import trade4fun.pojo.Token;
import trade4fun.pojo.User;
import trade4fun.pojo.Validatecode;
import trade4fun.service.UserService;
import trade4fun.utils.MsgCenter;
import trade4fun.utils.PasswordUtil;
import trade4fun.utils.RedisUtil;
import trade4fun.utils.Result;
import trade4fun.utils.UserHolder;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private TokenDao tokenDao;
	@Autowired
	private UserHolder userHolder;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
    private EventProducer eventProducer;

//===========================注册、登录、登出=========================================	
//用户注册操作：1、过滤不正确的参数 2、对密码进行加密 3、发送用于激活用户状态的邮件
	@Transactional
	public Result register(User user) {
        if (StringUtils.isBlank(user.getUsername())) {
            return Result.fail(MsgCenter.EMPTY_USERNAME);
        } else if (StringUtils.isBlank(user.getPassword())) {
            return Result.fail(MsgCenter.EMPTY_PASSWORD);
        } else if (16 < user.getPassword().replaceAll(" ", "").length()
                || user.getPassword().replaceAll(" ", "").length() < 6) {
            return Result.fail(MsgCenter.ERROR_PASSWORD_FORMAT);
        } else if (StringUtils.isBlank(user.getEmail())) {
            return Result.fail(MsgCenter.EMPTY_EMAIL);
        } else if (Pattern.compile("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$").
                matcher(user.getEmail()).find() == false) {   // 判断邮箱格式是否正确如:14_8@qw.df
            return Result.fail(MsgCenter.ERROR_EMAIL);
        } else if (userDao.selectByName(user.getUsername()) != null) {// 用户名已经被注册
            return Result.fail(MsgCenter.USER_USERNAME_EXISTS);
        } else if (userDao.selectByEmail(user.getEmail()) != null) {  // 邮箱已被注册
            return Result.fail(MsgCenter.EMAIL_REGISTERED);
        }
        user.setPassword(PasswordUtil.pwd2Md5(user.getPassword().replaceAll(" ", ""))); // 加密密码
        if (1 == userDao.insert(user)) { // 注册成功
        	//生成用户激活验证码10分钟后自动删除
/*          String uuid = UUID.randomUUID().toString();
            redisUtil.putEx("validatecode_" + user.getId(), uuid, Validatecode.TIMEOUT); // 存入redis
            EventModel eventModel = new EventModel(EventType.SEND_VALIDATE_EMAIL).setExts("mail", user.getEmail()).setExts("uid", user.getId().toString()).setExts("code", uuid);
            eventProducer.product(eventModel);*/
        	return Result.success(user.getId());
        }
        return Result.fail(MsgCenter.ERROR);
	}
//用户登录操作：保存token对象到数据库中并把user_id和token_uuid映射到redis中
	@Transactional
	public Result login(String username, String password, boolean remember, String ip, String device) {
		if(username == null || password == null) {
			return Result.fail(MsgCenter.EMPTY_LOGIN);
		}
		User user = userDao.selectByName(username);
		//用户名不存在或密码错误或用户已被删除
		if(user == null || !user.getPassword().equals(PasswordUtil.pwd2Md5(password))
				|| user.getStat().equals(User.STAT_DEL)) {
			return Result.fail(MsgCenter.ERROR_LOGIN);
		} else if (user.getStat().equals(User.STAT_RESTRICT)) {
			return Result.fail(MsgCenter.USER_RESTRICT);
		} else {
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			Token token = new Token();
			token.setUser_id(user.getId());
			token.setToken(uuid);
			token.setIp(ip);
			token.setDevice(device);
			token.setStat(token.STAT_OK);
			//设置令牌，如果保持登录， 过期时间就为7天，否则为1天
			if (remember) {
				token.setExpired_time(new Date(1000 * 60 * 60 * 24 * 7 + System.currentTimeMillis()));
			} else {
				token.setExpired_time(new Date(1000 * 60 * 60 * 24 + System.currentTimeMillis()));
			}
			tokenDao.insert(token);
			redisUtil.putEx(uuid, String.valueOf(user.getId()), 60 * 60 * 24);
			return Result.success(uuid);
		}
	}
//用户登出操作：删除redis并更改数据库中的token为失效状态
	public Result logout(String token) {
		if(token == null || token.length() != 32) {
			return Result.fail(MsgCenter.ERROR_PARAMS);
		}
		redisUtil.delete(token);
		tokenDao.updateStatByToken(Token.STAT_EXPIRED, token);
		return Result.success();
	}
	
	public Result updateUserInfo(User user) {
		if(userHolder.getUser() == null) {
			return Result.fail(MsgCenter.USER_NOT_LOGIN);
		} else if (StringUtils.isBlank(user.getEmail())) {
			return Result.fail(MsgCenter.EMPTY_EMAIL);
		} else if (Pattern.compile("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$").
				matcher(user.getEmail()).find() == false) { //判断邮箱格式是否正确
			return Result.fail(MsgCenter.ERROR_EMAIL);
		} else if (userDao.selectByEmail(user.getEmail()) != null && !userHolder.getUser().getEmail().equals(user.getEmail())) {
			return Result.fail(MsgCenter.EMAIL_REGISTERED);
		}
		user.setId(userHolder.getUser().getId());
		user.setPassword(null);
		user.setStat(null);
		if (1 == userDao.update(user)) {
			return Result.success();
		}
		return Result.fail(MsgCenter.ERROR);
	}

//============================激活用户部分===========================================
//生成验证码并发送验证邮件
	@Transactional
	public Result sendMail(User user) {
		if (user.getStat().equals(User.STAT_OK)) {
			return Result.fail(MsgCenter.USER_VALIDATED);
		}
		//生成激活码放到redis中，再通过邮件发送
		String uuid = UUID.randomUUID().toString();
		redisUtil.putEx("validatecode_" + user.getId(), uuid, Validatecode.TIMEOUT);
		EventModel eventModel = new EventModel(EventType.SEND_VALIDATE_EMAIL).setExts("mail", user.getEmail()).setExts("uid", user.getId().toString()).setExts("code", uuid);
		eventProducer.product(eventModel);
		return Result.success();
	}
//验证用户的验证码并激活: redis中得到的验证码和用户发来的验证码如果一致，则更新用户为激活状态
	@Transactional
	public Result validate(Integer uid, String code) {
		String uuid = redisUtil.get("validatecode_" + uid);
		if(code != null && code.length() == 36 && code.equals(uuid)) {
			User user = userDao.selectById(uid);
			user.setStat(User.STAT_OK);
			if(1 == userDao.update(user)) {//更新用户状态并删掉验证码
				redisUtil.delete("validatecode_" + uid);
				return Result.success();
			} else {
				return Result.fail(MsgCenter.ERROR);
			}
		}
		return Result.fail(MsgCenter.CODE_ERROR);
	}	
	

//=============================找回密码部分=====================================
//在拿到验证码的前提下更改密码
	public Result forgetPassword(String password, String email, String code) {
		User user = null;
		if(StringUtils.isBlank(email)) {
			return Result.fail(MsgCenter.EMPTY_EMAIL);
		} else if (Pattern.compile("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")
				.matcher(email).find() == false || (user = userDao.selectByEmail(email)) == null) {//判断邮箱格式以及更改前后的邮箱是一样的
			return Result.fail(MsgCenter.EMPTY_PASSWORD);
		} else if (StringUtils.isBlank(password)) {
			return Result.fail(MsgCenter.EMPTY_PASSWORD);
		} else if (16 < password.replaceAll(" ", "").length()
				|| password.replaceAll(" ", "").length() < 6) {
			return Result.fail(MsgCenter.ERROR_PASSWORD_FORMAT);
		}
		String uuid = redisUtil.get(email);
		if (code != null && code.length() == 36 && code.equals(uuid)) {
			user.setPassword(PasswordUtil.pwd2Md5(password.replaceAll(" ", "")));
			if (1 == userDao.update(user)) {
				redisUtil.delete(email); //验证成功后删除验证码
				return Result.success(user.getUsername());
			} else {
				return Result.fail(MsgCenter.ERROR);
			}
		}
		return Result.fail(MsgCenter.CODE_ERROR);
	}
//发送用于更改密码的验证码
	public Result sendFetchPwdMail(String email) {
		if(StringUtils.isBlank(email)) {
			return Result.fail(MsgCenter.EMPTY_EMAIL);
		} else if (Pattern.compile("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$").
				matcher(email).find() == false) { //判断邮箱格式是否正确
			return Result.fail(MsgCenter.ERROR_EMAIL);
		}
		User user = userDao.selectByEmail(email);
		if(user == null) {
			return Result.fail(MsgCenter.EMAIL_NOT_REGISTERED);
		}
		String uuid = UUID.randomUUID().toString();
		//将数据存入redis中， 固定时间后过期
		redisUtil.putEx(email, uuid, Validatecode.TIMEOUT);
		eventProducer.product(new EventModel(EventType.SEND_FIND_PWD_EMAIL).setExts("mail", user.getEmail()).setExts("code", uuid));
		return Result.success();
	}

//==============================用户其它操作=======================================
//在知道密码的前提下更改密码
	public Result updatePassword(String password) {
		if (StringUtils.isBlank(password)) {
			return Result.fail(MsgCenter.EMPTY_PASSWORD);
		} else if (16 < password.replaceAll(" ", "").length()
				|| password.replaceAll(" ", "").length() < 6) {
			return Result.fail(MsgCenter.ERROR_PASSWORD_FORMAT);
		}
		User user = userHolder.getUser();
		user.setPassword(PasswordUtil.pwd2Md5(password.replaceAll(" ", "")));
		if (1 == userDao.update(user)) {
			return Result.success();
		} else {
			return Result.fail(MsgCenter.ERROR);
		}
	}
//删除处于未激活状态且创建时间距在大于一小时的用户
	public void delNotValidateUser() {
		List<User> users = userDao.selectBystat(User.STAT_NOT_VALIDATE);
		Date date = new Date();
		date.setTime(date.getTime() - 1000 * 60 * 60);
		for (User user : users) {
			if (user.getCtime().before(date)) {
				userDao.deleteById(user.getId());
			}
		}
	}
//更改用户token为失效状态
	public void expireToken() {
		Date date = new Date();
		tokenDao.updateStatByDate(date, Token.STAT_EXPIRED);
	}
	
}
