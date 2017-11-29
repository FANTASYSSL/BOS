package com.wch.bos.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wch.bos.dao.IUserDao;
import com.wch.bos.domain.User;
import com.wch.bos.service.IUserService;
import com.wch.bos.utils.MD5Utils;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDao userDao;
	
	@Override
	public User login(User user) {
		String password = MD5Utils.md5(user.getPassword());
		return userDao.findUserByUsernameAndPassword(user.getUsername(), password);
	}

}
