package com.wch.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wch.bos.dao.ISubareaDao;
import com.wch.bos.domain.Subarea;
import com.wch.bos.service.ISubareaService;
import com.wch.bos.utils.PageBean;

@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {

	@Autowired
	private ISubareaDao subareaDao;
	
	@Override
	public void pageQuery(PageBean pageBean) {
		subareaDao.pageQuery(pageBean);
	}

	@Override
	public void save(Subarea model) {
		subareaDao.save(model);
	}

}
