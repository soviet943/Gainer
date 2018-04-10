package trade4fun.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import trade4fun.utils.UserHolder;

/**
 * 管理员权限拦截，如果不是管理员，那么跳转到管理员登录页面
 */
public class AdminInterceptor implements HandlerInterceptor {

	@Autowired
	private UserHolder userHolder;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(request.getRequestURI().equals("/admin/login")) {
			return true;
		}
		if(userHolder.getUser() != null && userHolder.getUser().getUsername().equals("admin")) {
			return true;
		}
		response.sendRedirect("/admin/login");
		return false;
	}
	
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}


}
