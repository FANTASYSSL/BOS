package com.wch.bos.service;

import java.util.List;

import com.wch.bos.domain.Function;
import com.wch.bos.utils.PageBean;

public interface IFunctionService {

	public List<Function> findAll();

	public void save(Function model);

	public void pageQuery(PageBean pageBean);

	public List<Function> findMenu();

}
