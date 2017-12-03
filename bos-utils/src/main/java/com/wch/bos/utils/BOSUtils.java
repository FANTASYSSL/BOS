package com.wch.bos.utils;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.wch.bos.domain.User;

public class BOSUtils {
	
	public static HttpSession getSession(){
		return ServletActionContext.getRequest().getSession();
	}
	
	public static User getLoinUser(){
		Object object = getSession().getAttribute("loginUser");
		return (User) (object == null?null:object);
	}
	
}
