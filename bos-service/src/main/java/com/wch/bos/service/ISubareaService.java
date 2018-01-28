package com.wch.bos.service;

import java.util.List;

import com.wch.bos.domain.Subarea;
import com.wch.bos.utils.PageBean;

public interface ISubareaService {

	public void pageQuery(PageBean pageBean);

	public void save(Subarea model);

	public List<Subarea> findAll();

	public List<Subarea> findListNotAssociation();

	public List<Subarea> findListByDecidedzoneId(String decidedzoneId);

	
}
