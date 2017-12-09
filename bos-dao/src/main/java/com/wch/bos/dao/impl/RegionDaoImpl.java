package com.wch.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wch.bos.dao.IRegionDao;
import com.wch.bos.dao.impl.base.impl.BaseDaoImpl;
import com.wch.bos.domain.Region;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao {

	@Override
	public List<Region> findListByQ(String q) {
		String hql = "FROM Region r where r.province LIKE ? OR r.city LIKE ? "
				+ " OR r.district LIKE ? OR r.shortcode LIKE ? OR r.citycode LIKE ? ";
		List<Region> regions = (List<Region>) this.getHibernateTemplate().find(hql, "%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%");
		return regions;
	}

	
	
}
