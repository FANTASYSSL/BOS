package com.wch.bos.service;

import com.wch.bos.domain.Subarea;
import com.wch.bos.utils.PageBean;

public interface ISubareaService {

	public void pageQuery(PageBean pageBean);

	public void save(Subarea model);
	
}