package com.ai.bdex.dataexchange.tradecenter.service.interfaces.page;

import java.util.List;

import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleAd;

/**
 * 
 * Description: <br>
 * Date: 2017年4月19日 <br>
 * Copyright (c) 2017 <br>
 * 
 * @author liangwy
 */
public interface IPageModuleAdSV {
	public PageModuleAd queryPageModuleAdById(Integer id) throws Exception;
	public List<PageModuleAd> queryPageModuleAdList(PageModuleAd pageModuleAd) throws Exception;

}
