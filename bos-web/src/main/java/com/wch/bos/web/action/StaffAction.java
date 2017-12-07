package com.wch.bos.web.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wch.bos.domain.Staff;
import com.wch.bos.service.IStaffService;
import com.wch.bos.utils.PageBean;
import com.wch.bos.web.action.base.BaseAction;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IStaffService staffService;
	
	private int page;
	private int rows;
	
	private String ids;
	
	public String add() {
		staffService.save(model);
		return LIST;
	} 
	
	public String pageQuery() throws IOException {
		
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(page);
		pageBean.setPageSize(rows);
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
		pageBean.setDetachedCriteria(detachedCriteria);
		
		staffService.pageQuery(pageBean);
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"currentPage","detachedCriteria","pageSize"});
		
		String json = JSONObject.fromObject(pageBean,jsonConfig).toString();
		
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().println(json);
		
		return NONE;
	}

	public String deleteBatch() {
		staffService.deleteBatch(ids);
		return LIST;
	}
	
	public String edit() throws IllegalAccessException, InvocationTargetException{
		Staff staff = staffService.findStaffById(model.getId());
		BeanUtils.copyProperties(staff, model);
		staffService.update(staff);
		return LIST;
	}
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
}
