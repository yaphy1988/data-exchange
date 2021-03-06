package com.ai.bdex.dataexchange.tradecenter.service.interfaces.page;

import java.util.List;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleAd;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleAdReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleAdRespDTO;

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
	public PageResponseDTO<PageModuleAdRespDTO>  queryPageModuleAdPageInfo(PageModuleAdReqDTO moduleAdDTO) throws Exception;
    public int updatePageModuleAdInfo(PageModuleAdReqDTO moduleAdDTO) throws Exception;
	public int insertPageModuleAdInfo(PageModuleAdReqDTO moduleAdDTO) throws Exception;
}
