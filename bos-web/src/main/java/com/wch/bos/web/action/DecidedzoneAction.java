package com.wch.bos.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wch.bos.domain.Decidedzone;
import com.wch.bos.service.IDecidedzoneService;
import com.wch.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {
	
	private static final long serialVersionUID = 1L;
	
	private String[] subareaid;
	
	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}

	@Autowired
	private IDecidedzoneService decidedzoneService;
	
	public String add(){
		decidedzoneService.save(model,subareaid);
		return LIST;
	}
	
	public String pageQuery(){
		decidedzoneService.pageQuery(pageBean);
		this.java2Json(pageBean, new String[]{"currentPage",
				"detachedCriteria","pageSize","subareas","decidedzones"});
		return NONE;
	}
	
	

}
