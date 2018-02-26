package com.wch.bos.service;

import java.util.List;

import com.wch.bos.domain.Role;
import com.wch.bos.utils.PageBean;

public interface IRoleService {

	public void save(Role role, String functionIds);

	public void pageQuery(PageBean pageBean);

	public List<Role> findAll();

}
