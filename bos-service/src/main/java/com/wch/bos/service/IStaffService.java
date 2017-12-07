package com.wch.bos.service;

import com.wch.bos.domain.Staff;
import com.wch.bos.utils.PageBean;

public interface IStaffService {

	public void save(Staff model);

	public void pageQuery(PageBean pageBean);

	public void deleteBatch(String ids);

	public Staff findStaffById(String id);

	public void update(Staff staff);

}
