package com.wch.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wch.bos.dao.IRegionDao;
import com.wch.bos.domain.Region;
import com.wch.bos.service.IRegionService;

@Service
@Transactional
public class RegionServiceImpl implements IRegionService {

	@Autowired
	private IRegionDao regionDao;
	
	@Override
	public void saveBatch(List<Region> regions) {
		for (Region region : regions) {
			regionDao.saveOrUpdate(region);
		}
	}

}
