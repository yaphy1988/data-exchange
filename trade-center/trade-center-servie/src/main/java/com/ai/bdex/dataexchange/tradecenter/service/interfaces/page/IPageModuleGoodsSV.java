package com.ai.bdex.dataexchange.tradecenter.service.interfaces.page;


import java.util.List;

import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleGoods;

/**
 * 
 * Description: <br>
 * Date: 2017年4月18日 <br>
 * Copyright (c) 2017 <br>
 * 
 * @author liangwy
 */
public interface IPageModuleGoodsSV {
	public PageModuleGoods queryPageModuleGoodsById(Integer pmgId) throws Exception;
	public List<PageModuleGoods> queryPageModuleGoodsList(PageModuleGoods pageModuleGoods) throws Exception;
}
