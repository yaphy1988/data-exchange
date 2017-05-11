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
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.SortContentReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.SortContentRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.SortInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.SortInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.*;

public interface    IPageDisplayRSV {
	
	public SortInfoRespDTO querySortInfoById(SortInfoReqDTO sortInfoReqDTO) throws Exception;
	
	public SortContentRespDTO querysortContenById(SortContentReqDTO sortContentReqDTO) throws Exception;
	
    public  List<SortInfoRespDTO>  querySortInfos(SortInfoRespDTO sortInfoRespDTO) throws Exception;

    public PageResponseDTO<PageNewsInfoRespDTO> queryPageNewsInfoList(PageNewsInfoReqDTO sortInfoDTO) throws Exception;

    public PageNewsInfoRespDTO queryPageNewsInfoById(Integer PageInfoid) throws Exception;

    public List<PageHotSearchRespDTO> queryPageHotSearchList(PageHotSearchRespDTO pageHotSearchRespDTO) throws Exception;

    public List<PageHeaderNavRespDTO> queryPageHeaderNavList(PageHeaderNavRespDTO pageHeaderNavRespDTO) throws Exception;
	
    public PageModuleRespDTO queryPageModuleById(Integer moduleId) throws Exception;
	
    public List<PageModuleRespDTO> queryPageModuleList(PageModuleReqDTO pageModuleReqDTO) throws Exception;

    public List<PageModuleRespDTO> queryPageModuleInfoList(PageModuleReqDTO pageModuleReqDTO) throws Exception;

    public List<PageModuleAdRespDTO> queryPageModuleAdList(PageModuleAdRespDTO pageModuleAdRespDTO) throws Exception;
    
	public PageModuleAdRespDTO queryPageModuleAdById(Integer adId) throws Exception;
	
    public PageResponseDTO<PageModuleGoodsRespDTO>  queryPageModuleGoodsList(PageModuleGoodsReqDTO goodsReqDTO) throws Exception;
    
    public List<PageModuleAdPropRespDTO> queryPageModuleAdPropList(PageModuleAdPropRespDTO pageModuleAdpropRespDTO) throws Exception;
    
    public int saveDataCustomizationRsv(DataCustomizationRespDTO dataCustomizationRespDTO) throws Exception;
    
    public List<SortContentRespDTO> querysortContenList(SortContentReqDTO sortContentReqDTO) throws Exception;

    public PageResponseDTO<PageModuleAdRespDTO> queryPageModuleAdPageInfo(PageModuleAdReqDTO moduleAdDTO) throws Exception;

    public long insertPageNewsInfo(PageNewsInfoReqDTO newsInfoReqDTO) throws Exception;
    
    public long updatePageNewsInfoByKey(PageNewsInfoReqDTO newsInfoReqDTO) throws Exception;
    
	public int updatePageModuleAdByKey(PageModuleAdReqDTO reqDTO) throws Exception;
	
	public int insertPageModuleAdInfo(PageModuleAdReqDTO moduleAdDTO) throws Exception;
	public int updatePageModule(PageModuleReqDTO reqDTO) throws Exception;
	
	public PageAdPalceRespDTO queryPageAdPlace(PageAdPalceReqDTO adPalceReqDTO) throws Exception;
	
	public List<PageAdPalceRespDTO> queryPageAdPalceList(PageAdPalceReqDTO adPalceReqDTO) throws Exception;

	public long insertSortContent(SortContentReqDTO sortContentReqDTO)throws Exception;

	public long updateSortContentById(SortContentReqDTO sortContentReqDTO)throws Exception;

	public long insertSortInfo(SortInfoReqDTO sortInfoReqDTO)throws Exception;

    public long updateSortInfoById(SortInfoReqDTO sortInfoReqDTO)throws Exception;
    public PageResponseDTO<DataCustomizationRespDTO> queryDataCustomizationInfo(DataCustomizationReqDTO dataCustomizationReqDTO) throws Exception;
    public int updateDataCustomizationStatus(DataCustomizationReqDTO dataCustomizationReqDTO) throws Exception;

}
