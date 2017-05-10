package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.page;
 
 
import java.util.List;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.DataCustomizationRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageAdPalceReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageAdPalceRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageHeaderNavRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageHotSearchRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleAdPropRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleAdReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleAdRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleGoodsReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleGoodsRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageNewsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageNewsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.SortContentRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.SortInfoRespDTO;

public interface IPageModuleRSV {
	public PageResponseDTO<PageModuleGoodsRespDTO> queryPageModuleGoodsPage(PageModuleGoodsReqDTO moduleGoodsReqDTO) throws Exception ;
	public List<PageModuleGoodsRespDTO> queryPageModuleGoodsInfoList(PageModuleGoodsReqDTO moduleGoodsReqDTO) throws Exception;
	public int insertPageModuleGoods(PageModuleGoodsReqDTO moduleGoodsReqDTO)throws Exception ;
	public int updatePageModuleGoods(PageModuleGoodsReqDTO moduleGoodsReqDTO)throws Exception ;
}
