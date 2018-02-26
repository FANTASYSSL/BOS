package com.wch.bos.service;

import com.wch.bos.domain.User;
import com.wch.bos.utils.PageBean;

public interface IUserService {
	
	public User login(User user);

	public void editPassword(String id, String password);
	
	public void save(User model, String[] roleIds);

	public void pageQuery(PageBean pageBean);
	
}
