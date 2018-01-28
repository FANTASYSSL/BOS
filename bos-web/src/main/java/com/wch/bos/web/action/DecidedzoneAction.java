package com.wch.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wch.bos.crm.Customer;
import com.wch.bos.crm.ICustomerService;
import com.wch.bos.domain.Decidedzone;
import com.wch.bos.service.IDecidedzoneService;
import com.wch.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {
	
	private static final long serialVersionUID = 1L;
	
	private String[] subareaid;
	
	private List<Integer> customerIds;
	
	public List<Integer> getCustomerIds() {
		return customerIds;
	}

	public void setCustomerIds(List<Integer> customerIds) {
		this.customerIds = customerIds;
	}

	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}

	@Autowired
	private IDecidedzoneService decidedzoneService;
	
	@Autowired
	private ICustomerService customerService;
	
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
	
	public String findListNotAssociation() {
		List<Customer> list = customerService.findListNotAssociation();
		this.java2Json(list, new String[]{});
		return NONE;
	}
	
	public String findListHasAssociation(){
		List<Customer> list = customerService.findListHasAssociation(model.getId());
		this.java2Json(list, new String[]{});
		return NONE;
	}
	
	public String assigncustomerstodecidedzone(){
		customerService.assigncustomerstodecidedzone(model.getId(), customerIds);
		return LIST;
	}
	

}
