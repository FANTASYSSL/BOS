package com.wch.bos.web.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wch.bos.domain.User;
import com.wch.bos.service.IUserService;
import com.wch.bos.web.action.base.BaseAction;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	
	@Autowired
	private IUserService userService;
	
	private String checkCode;
	
	public String login(){
		String validateCode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		
		if (StringUtils.isNotBlank(validateCode) && validateCode.equals(checkCode)) {
			
			User user = userService.login(model);
			if (user != null) {
				ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
				return HOME;
			}else{
				this.addActionError("用户名或密码输入错误！");
				return LOGIN;
			}
		} else {
			this.addActionError("输入的验证码错误！");
			return LOGIN;
		}
	}
	
	

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	
	
	
}
