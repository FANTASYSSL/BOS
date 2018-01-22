package com.wch.bos.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wch.bos.dao.IDecidedzoneDao;
import com.wch.bos.dao.ISubareaDao;
import com.wch.bos.domain.Decidedzone;
import com.wch.bos.domain.Subarea;
import com.wch.bos.service.IDecidedzoneService;
import com.wch.bos.utils.PageBean;

@Service
@Transactional
public class DecidedzoneServiceImpl implements IDecidedzoneService {

	@Resource
	private IDecidedzoneDao decidedzoneDao;
	
	@Autowired
	private ISubareaDao subareaDao;
	
	@Override
	public void save(Decidedzone model, String[] subareaid) {
		decidedzoneDao.save(model);
		for (String id : subareaid) {
			Subarea subarea = subareaDao.findById(id);
			subarea.setDecidedzone(model);
		}
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		subareaDao.pageQuery(pageBean);
	}
	
	
	

}
