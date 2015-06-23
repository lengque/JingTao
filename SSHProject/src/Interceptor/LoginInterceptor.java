package Interceptor;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.print("开始验证");
		// 取得请求相关的ActionContext实例
		ActionContext ctx = invocation.getInvocationContext();
		// 获取session
		Map session = ctx.getSession();
		// 获取用户名
		String userName = session.get("userName").toString();
		System.out.println(userName);
		// 获取密码
		String password = session.get("password").toString();
		System.out.println(password);
		if (null!=userName && null!=password && StringUtils.isNotBlank(userName)
				&& StringUtils.isNotBlank(password)) {
			System.out.println("登陆验证通过");
			return invocation.invoke();
		}

		ctx.put("tip", "你还没有登录");
		System.out.print("你还没有登录");
		return Action.LOGIN;

	}
}
