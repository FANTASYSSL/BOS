package com.wch.bos.service;

import java.util.List;

import com.wch.bos.domain.Region;
import com.wch.bos.utils.PageBean;

public interface IRegionService{

	public void saveBatch(List<Region> regions);

	public void pageQuery(PageBean pageBean);

	public List<Region> findAll();

	public List<Region> findListByQ(String q);

}
