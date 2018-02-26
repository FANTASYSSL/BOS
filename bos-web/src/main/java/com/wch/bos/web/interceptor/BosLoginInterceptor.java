package com.wch.bos.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.wch.bos.domain.User;
import com.wch.bos.utils.BOSUtils;

public class BosLoginInterceptor extends MethodFilterInterceptor{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		
		User user = BOSUtils.getLoginUser();
		if (user == null) {
			return "login";
		}
		return invocation.invoke();
	}
	
	
	
}



