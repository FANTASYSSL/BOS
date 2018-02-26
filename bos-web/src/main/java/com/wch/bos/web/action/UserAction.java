package com.wch.bos.web.action;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wch.bos.domain.User;
import com.wch.bos.service.IUserService;
import com.wch.bos.utils.BOSUtils;
import com.wch.bos.utils.MD5Utils;
import com.wch.bos.web.action.base.BaseAction;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	
	@Autowired
	private IUserService userService;
	
	private String checkCode;
	
	/**
	 * 修改使用shiro认证
	 * @return
	 */
	public String login(){
		
		String validateCode = (String) BOSUtils.getSession().getAttribute("key");
		
		if (StringUtils.isNotBlank(validateCode) && validateCode.equals(checkCode)) {
			/*User user = userService.login(model);
			if (user != null) {
				ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
				return HOME;
			}else{
				this.addActionError("用户名或密码输入错误！");
				return LOGIN;
			}*/
			Subject subject = SecurityUtils.getSubject();
			AuthenticationToken token = new UsernamePasswordToken(model.getUsername(),MD5Utils.md5(model.getPassword()));
			try {
				subject.login(token);
			} catch (AuthenticationException e) {
				e.printStackTrace();
//				UnknownAccountException，IncorrectCredentialsException
				this.addActionError("用户名或密码不正确！");
				return LOGIN;
			}
			User user = (User) subject.getPrincipal();
			ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
			return HOME;
		} else {
			this.addActionError("输入的验证码错误！");
			return LOGIN;
		}
	}
	/*	public String login(){
			String validateCode = (String) BOSUtils.getSession().getAttribute("key");
			
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
	*/	
	public String editPassword() throws IOException {
		String flag = "1";
		User user = BOSUtils.getLoginUser();
		try {
			userService.editPassword(user.getId(),model.getPassword());
		} catch (Exception e) {
			flag = "0";
			e.printStackTrace();
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(flag);;
		return NONE;
	}
	
	public String logout(){
		BOSUtils.getSession().invalidate();
		return LOGIN;
	}
	
	//属性驱动，接收多个角色id
	private String[] roleIds;
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}
	
	/**
	 * 添加用户
	 */
	public String add(){
		userService.save(model,roleIds);
		return LIST;
	}

	/**
	 * 用户数据分页查询
	 */
	public String pageQuery(){
		userService.pageQuery(pageBean);
		this.java2Json(pageBean, new String[]{"noticebills","roles"});
		return NONE;
	}
	
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getCheckCode() {
		return checkCode;
	}
	
}
