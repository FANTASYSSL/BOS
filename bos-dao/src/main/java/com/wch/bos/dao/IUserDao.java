package com.wch.bos.dao;

import com.wch.bos.dao.impl.base.IBaseDao;
import com.wch.bos.domain.User;

public interface IUserDao extends IBaseDao<User> {
	
	public User findUserByUsernameAndPassword(String username,String password);
	
}
