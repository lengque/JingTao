package Interceptor;

import java.util.Map;

import model.User;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.UserUtil;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	
	private Log logger = LogFactory.getLog(this.getClass().getName());
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("开始进行登录验证~~~");
		}
		
		// 取得请求相关的ActionContext实例
		ActionContext ctx = invocation.getInvocationContext();
		// 获取session
		Map session = ctx.getSession();
		// 获取用户
		User user = (User)session.get(UserUtil.User_Login);
		Boolean isLogin = (Boolean) session.get(UserUtil.User_Is_Login);
		
		if(isLogin && null != user){
			if(logger.isDebugEnabled()){
				logger.debug("用户已登陆");
			}
			
			return invocation.invoke();
		}
		
		if(logger.isDebugEnabled()){
			logger.debug("登录验证失败");
		}
		
		ctx.put("tip", "你还没有登录");
		
		return Action.LOGIN;
	}
}
