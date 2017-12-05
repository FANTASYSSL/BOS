package com.wch.bos.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wch.bos.dao.IStaffDao;
import com.wch.bos.domain.Staff;
import com.wch.bos.service.IStaffService;
import com.wch.bos.utils.PageBean;

@Service
@Transactional
public class StaffServiceImpl implements IStaffService {

	@Autowired
	public IStaffDao staffDao;
	
	@Override
	public void save(Staff model) {
		staffDao.save(model);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		staffDao.pageQuery(pageBean);
	}

	@Override
	public void deleteBatch(String ids) {
		if (StringUtils.isNotBlank(ids)) {
			for (String id : ids.split(",")) {
				staffDao.executeUpdate("staff.delete", id);
			}
		}
	}

}
