package com.wch.bos.service;

import com.wch.bos.domain.Decidedzone;
import com.wch.bos.utils.PageBean;

public interface IDecidedzoneService {

	public void save(Decidedzone model, String[] subareaid);

	public void pageQuery(PageBean pageBean);

}
