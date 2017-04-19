package com.ai.bdex.dataexchange.tradecenter.service.interfaces.page;

import java.util.List;

import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleAdprop;;
/**
 * 
 * Description: <br>
 * Date: 2017年4月19日 <br>
 * Copyright (c) 2017 <br>
 * 
 * @author liangwy
 */
public interface IPageModuleAdpropSV {
	public PageModuleAdprop queryPageModuleAdpropById(Integer id) throws Exception;
	public List<PageModuleAdprop> queryPageModuleAdpropList(PageModuleAdprop pageModuleAdprop) throws Exception;

}
