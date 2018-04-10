package trade4fun.interceptor;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/*
 * 拦截器，根据token获取用户身份
 * 先从缓存中查找，如果查找不到再从数据库中找
 */
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import trade4fun.dao.TokenDao;
import trade4fun.dao.UserDao;
import trade4fun.pojo.Token;
import trade4fun.pojo.User;
import trade4fun.utils.RedisUtil;
import trade4fun.utils.UserHolder;
@Component
public class AuthInterceptor implements HandlerInterceptor {
	@Autowired
	private TokenDao tokenDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private UserHolder userHolder;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		//如果请求静态资源直接pass
		if(request.getRequestURI().matches("^/images/.+\\.(png|jpg)$") || request.getRequestURI().matches("^/css/.+\\.css$")
			|| request.getRequestURI().matches("^/js/.+\\.js$")) {
			return true;
		}
		String token = null;
//1、获取用户cookie
		if(request.getCookies() != null) {
			for(Cookie cookie : request.getCookies()) {
				if(cookie.getName().equals("token")) {
					token = cookie.getValue();
				}
			}
		}
//2、存在cookie，尝试从redis中根据cookie值查找用户id
		if(token != null) {
			String userid  = redisUtil.get(token);
			int uid = 0;
		//3-1、若redis中没有token，则从数据库中查找没有过期的token
			if(userid == null) {
				Date now = new Date();
				Token t = tokenDao.selectByTokenAndStat(token, Token.STAT_OK);
				//4-1、不存在没过期的token   或者     存在，但是其保质期实际已经过了，状态还没改过来（定时器每隔一段时间更新状态的缘故）
				if(t == null || t.getExpired_time().before(now)) {
					return true;
				}
				//4-2、存在没过期的token，但是缓存中没有了（发生了点意外。。），则重新将token加入缓存中
				uid = t.getUser_id();
				long oneday = 86400000L;
				long expired = t.getExpired_time().getTime() - now.getTime();
				if(expired > oneday) {//如果token剩余有效期大于1天，加入缓存中的token有效期为1天
					redisUtil.putEx(token, String.valueOf(uid), 60*60*24);
				} else {//否则有效期为剩下的时间
					redisUtil.putEx(token, String.valueOf(uid), (int) (expired/1000));
				}
			} 
		//3-2、缓存中有的话直接获取user_id
			else {
				uid = Integer.valueOf(userid);
			}
			// token有效，将当前用户暂时存放起来，之后在所有的地方都可以通过依赖注入的UserHolder获取当前用户
            User user = userDao.selectById(uid);
            if (user == null) {
                return true;
            }
            userHolder.setUser(user);
            // 跳过post方法
            if (request.getMethod().equals("POST")) {
                return true;
            }
            // 如果用户登陆成功但是没有验证邮箱，跳转到邮箱验证页面(防止请求的url就是验证页时会循环跳转)
            /*if (user.getStat().equals(User.STAT_NOT_VALIDATE) && !request.getRequestURI().equals("/user/validate")) {
                response.sendRedirect("user/validate");
                return false;
            } */
            else if (user.getStat().equals(User.STAT_DEL)) {
                userHolder.remove();
            }
        }
        return true;
	}
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView)
			throws Exception {
		if(modelAndView == null) {
			return;
		}
		User user = userHolder.getUser();
		if(request.getMethod().equals("GET") && user != null) {
			modelAndView.getModel().put("user", user.getUsername());
			modelAndView.getModel().put("uid", user.getUsername());
			modelAndView.getModel().put("money", user.getMoney());
			modelAndView.getModel().put("cartNum", user.getCartNum());
		}
	}
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception)
			throws Exception {
		userHolder.remove();
	}
}
