package com.wch.bos.dao;

import java.util.List;

import com.wch.bos.dao.impl.base.IBaseDao;
import com.wch.bos.domain.Region;

public interface IRegionDao extends IBaseDao<Region>{

	public List<Region> findListByQ(String q);

}
