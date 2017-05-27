package com.ai.bdex.dataexchange.tradecenter.service.interfaces.page;


import java.util.List;

import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModule;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleReqDTO;

/**
 * 
 * Description: <br>
 * Date: 2017年4月18日 <br>
 * Copyright (c) 2017 <br>
 * 
 * @author liangwy
 */
public interface IPageModuleSV {
	public PageModule queryPageModuleById(Integer moduleId) throws Exception;
	public List<PageModule> queryPageModuleList(PageModule pageModule) throws Exception;
	public int updatePageModule(PageModuleReqDTO moduleDTO) throws Exception;
}
