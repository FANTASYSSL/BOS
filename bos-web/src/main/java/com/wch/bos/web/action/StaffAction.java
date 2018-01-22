package com.wch.bos.web.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.test.JSONAssert;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IStaffService staffService;
	
/*	private int page;
	private int rows;*/
	
	private String ids;
	
	public String add() {
		staffService.save(model);
		return LIST;
	} 
	
	public String pageQuery() throws IOException {
		staffService.pageQuery(pageBean);
		this.java2Json(pageBean, new String[]{"currentPage","detachedCriteria","pageSize"});
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
	
	public String listajax(){
		List<Staff> list = staffService.findListNotDelete();
		this.java2Json(list, new String[]{"decidedzones"});
		return NONE;
	}
	
	
	
	/*public int getPage() {
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
	}*/

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
}
