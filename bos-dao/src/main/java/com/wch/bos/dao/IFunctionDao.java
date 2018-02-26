package com.wch.bos.dao;

import java.util.List;

import com.wch.bos.dao.impl.base.IBaseDao;
import com.wch.bos.domain.Function;

public interface IFunctionDao extends IBaseDao<Function> {
	public List<Function> findFunctionListByUserId(String id);
	public List<Function> findAllMenu();
	public List<Function> findMenuByUserId(String id);
}
