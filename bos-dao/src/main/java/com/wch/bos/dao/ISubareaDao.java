package com.wch.bos.dao;

import java.util.List;

import com.wch.bos.dao.impl.base.IBaseDao;
import com.wch.bos.domain.Subarea;

public interface ISubareaDao extends IBaseDao<Subarea> {

	List<Object> findSubareasGroupByProvince();

}
