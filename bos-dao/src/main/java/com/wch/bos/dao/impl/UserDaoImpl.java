package com.wch.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wch.bos.dao.IUserDao;
import com.wch.bos.dao.impl.base.impl.BaseDaoImpl;
import com.wch.bos.domain.User;

@Repository
@SuppressWarnings("all")
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

	@Override
	@SuppressWarnings("unchecked")
	public User findUserByUsernameAndPassword(String username, String password) {
		String hql = "from User u where u.username = ? and u.password = ?";
		List<User> users = (List<User>) getHibernateTemplate().find(hql, username,password);
		if (users != null && !users.isEmpty()) {
			return users.get(0);
		}
		return null;
	}

	@Override
	public User findUserByUsername(String username) {
		String hql = "from User u where u.username = ? ";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}


}
