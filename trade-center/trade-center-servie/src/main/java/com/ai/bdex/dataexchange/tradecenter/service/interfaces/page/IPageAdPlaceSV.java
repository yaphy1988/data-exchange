package com.ai.bdex.dataexchange.tradecenter.service.interfaces.page;

import java.util.List;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageAdPalceReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageAdPalceRespDTO;;
/**
 * 
 * Description: <br>
 * Date: 2017年4月19日 <br>
 * Copyright (c) 2017 <br>
 * 
 * @author liangwy
 */
public interface IPageAdPlaceSV {
	public PageAdPalceRespDTO queryPageAdPlace(PageAdPalceReqDTO pageAdPalceReqDTO) throws Exception;
	public List<PageAdPalceRespDTO> queryPageAdPalceList(PageAdPalceReqDTO pageAdPalceReqDTO) throws Exception;

}
