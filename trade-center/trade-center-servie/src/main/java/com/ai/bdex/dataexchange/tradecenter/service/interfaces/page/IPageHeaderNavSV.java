package com.ai.bdex.dataexchange.tradecenter.service.interfaces.page;

import com.ai.bdex.dataexchange.tradecenter.dao.model.PageHeaderNav;
 
public interface IPageHeaderNavSV {
	public  PageHeaderNav  queryPageHeaderNavById(Integer head_id) throws Exception ;
	public  PageHeaderNav  queryPageHeaderNavList(PageHeaderNav example) throws Exception ;
	

	
}
