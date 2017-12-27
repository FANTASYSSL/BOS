package com.wch.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wch.bos.domain.Region;
import com.wch.bos.service.IRegionService;
import com.wch.bos.utils.PageBean;
import com.wch.bos.utils.PinYin4jUtils;
import com.wch.bos.web.action.base.BaseAction;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private IRegionService regionService;

	private File regionFile;
	
	private String q;
	
	private String add(){
		regionService.save(model);
		return LIST;
	}
	
	public String importXls() throws FileNotFoundException, IOException {
		List<Region> regions = new ArrayList<>();
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(regionFile));
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		for (Row row : hssfSheet) {
			if (row.getRowNum() == 0) {
				continue;
			}
			String id = row.getCell(0).getStringCellValue();
			String province  = row.getCell(1).getStringCellValue();
			String city = row.getCell(2).getStringCellValue();
			String district = row.getCell(3).getStringCellValue();
			String postcode = row.getCell(4).getStringCellValue();
			
			Region region = new Region(id, province, city, district, postcode, null, null, null);
			
			province = province.substring(0, province.length()-1);
			city = city.substring(0, city.length()-1);
			district = district.substring(0, district.length()-1);
			String info = province + city + district;
			
			String[] headByString = PinYin4jUtils.getHeadByString(info);
			String shortcode = StringUtils.join(headByString);
			String cityCode = PinYin4jUtils.hanziToPinyin(city,"");
			
			region.setShortcode(shortcode);
			region.setCitycode(cityCode);
			regions.add(region);
		}
		regionService.saveBatch(regions);
		return NONE;
	}
	
	public String pageQuery() throws IOException{
		regionService.pageQuery(pageBean);
		this.java2Json(pageBean, new String[]{"currentPage","detachedCriteria","pageSize","subareas"});
		return NONE;
	}
	
	public String listAjax(){
		List<Region> list = null;
		if (StringUtils.isNotBlank(q)) {
			list = regionService.findListByQ(q);
		}else{
			list = regionService.findAll();
		}
		this.java2Json(list,new String[]{"subareas"});
		return NONE;
	}
	
	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

}
