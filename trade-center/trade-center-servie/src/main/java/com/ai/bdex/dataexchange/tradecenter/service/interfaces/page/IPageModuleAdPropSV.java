package com.ai.bdex.dataexchange.tradecenter.service.interfaces.page;

import java.util.List;

import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleAdProp;;
/**
 * 
 * Description: <br>
 * Date: 2017年4月19日 <br>
 * Copyright (c) 2017 <br>
 * 
 * @author liangwy
 */
public interface IPageModuleAdPropSV {
	public PageModuleAdProp queryPageModuleAdPropById(Integer id) throws Exception;
	public List<PageModuleAdProp> queryPageModuleAdPropList(PageModuleAdProp pageModuleAdProp) throws Exception;

}
