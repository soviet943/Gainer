package trade4fun.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import trade4fun.utils.UserHolder;

/*
 * 拦截器，用于拦截某些需要登录的功能，如果没有登录会被拦截，并跳转到登录页面
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UserHolder userHolder;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		if(userHolder.getUser() == null) {
			response.sendRedirect("/t4f/login");
			return false;
		}
		return true;
	}
	
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}
}
