package com.wch.bos.web.action;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wch.bos.domain.Subarea;
import com.wch.bos.service.ISubareaService;
import com.wch.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ISubareaService subareaService;
	
	public String add(){
		subareaService.save(model);
		return LIST;
	}
	
	public String pageQuery(){
		DetachedCriteria criteria = pageBean.getDetachedCriteria();
		String addresskey = model.getAddresskey();
		
		subareaService.pageQuery(pageBean);
		this.java2Json(pageBean,new String[]{"currentPage","detachedCriteria","pageSize",
				"decidedzone","subareas"});
		
		
		
		return NONE;
	}
	
	
}
