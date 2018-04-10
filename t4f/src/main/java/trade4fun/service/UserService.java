package trade4fun.service;

import trade4fun.pojo.User;
import trade4fun.utils.Result;

public interface UserService {

    /**
     * 注册
     */
    Result register(User user);

    /**
     * 给用户注册的邮箱发送验证码
     */
    Result sendMail(User user);

    /**
     * 邮箱验证
     */
    Result validate(Integer uid, String code);

    /**
     * 登录
     * 登陆时如果选择了“记住我”选项，那么token保持7天，否则保存1天
     * 登陆成功，将token存在数据库中记录，同时存入缓存，过期时间为1天
     * 每次请求时拦截器先从缓存中查找，如果没有再去数据库中查找
     */
    Result login(String username, String password, boolean remember, String ip, String device);

    /**
     * 登出
     */
    Result logout(String token);

    /**
     * 更新用户信息
     */
    Result updateUserInfo(User user);

    /**
     * 更新密码
     */
    Result updatePassword(String password);

    /**
     * 忘记密码
     */
    Result forgetPassword(String password, String email, String code);

    /**
     * 忘记密码时需要给邮箱发送验证码
     */
    Result sendFetchPwdMail(String email);

    /**
     * 删除没有验证的用户
     */
    void delNotValidateUser();

    /**
     * 删除已经过期的token
     */
    void expireToken();
}
