package com.ai.bdex.dataexchange.tradecenter.service.interfaces.page;


import java.util.List;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleGoods;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleGoodsReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleGoodsRespDTO;

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
	public PageResponseDTO<PageModuleGoodsRespDTO>  queryPageModuleGoodsList(PageModuleGoodsReqDTO moduleGoodsReqDTO) throws Exception;
	public List<PageModuleGoodsRespDTO> queryPageModuleGoodsInfoList(PageModuleGoodsReqDTO moduleGoodsReqDTO) throws Exception;
	public PageResponseDTO<PageModuleGoodsRespDTO> queryPageModuleGoodsPage(PageModuleGoodsReqDTO moduleGoodsReqDTO) throws Exception;
	public int insertPageModuleGoods(PageModuleGoodsReqDTO moduleGoodsReqDTO)throws Exception;
	public int updatePageModuleGoods(PageModuleGoodsReqDTO moduleGoodsReqDTO)throws Exception;
	/**
	 * 已选择商品
	 * @param moduleGoodsReqDTO
	 * @return
	 * @throws Exception
	 */
	public PageResponseDTO<PageModuleGoodsRespDTO> querSelPageModuleGoodsPage(PageModuleGoodsReqDTO moduleGoodsReqDTO) throws Exception;

}
