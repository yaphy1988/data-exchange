package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.page;
 
 
import java.util.List;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.DataCustomizationRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageHeaderNavRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageHotSearchRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleAdPropRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleAdReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleAdRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleGoodsRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageNewsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageNewsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.SortContentRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.SortInfoRespDTO;

public interface    IPageDisplayRSV {
    public  List<SortInfoRespDTO>  querySortInfos(SortInfoRespDTO sortInfoRespDTO) throws Exception;

    public PageResponseDTO<PageNewsInfoRespDTO> queryPageNewsInfoList(PageNewsInfoReqDTO sortInfoDTO) throws Exception;

    public PageNewsInfoRespDTO queryPageNewsInfoById(Integer PageInfoid) throws Exception;

    public List<PageHotSearchRespDTO> queryPageHotSearchList(PageHotSearchRespDTO pageHotSearchRespDTO) throws Exception;

    public List<PageHeaderNavRespDTO> queryPageHeaderNavList(PageHeaderNavRespDTO pageHeaderNavRespDTO) throws Exception;
    
    public List<PageModuleRespDTO> queryPageModuleList(PageModuleReqDTO pageModuleReqDTO) throws Exception;
    
    public List<PageModuleAdRespDTO> queryPageModuleAdList(PageModuleAdRespDTO pageModuleAdRespDTO) throws Exception;
    
    public PageResponseDTO<PageModuleGoodsRespDTO>  queryPageModuleGoodsList(PageModuleGoodsRespDTO pageModuleGoodsRespDTO) throws Exception;
    
    public List<PageModuleAdPropRespDTO> queryPageModuleAdPropList(PageModuleAdPropRespDTO pageModuleAdpropRespDTO) throws Exception;
    
    public int saveDataCustomizationRsv(DataCustomizationRespDTO dataCustomizationRespDTO) throws Exception;
    
    public List<SortContentRespDTO> querysortContenList(SortContentRespDTO sortContentRespDTO) throws Exception;

    public PageResponseDTO<PageModuleAdRespDTO> queryPageModulePageInfo(PageModuleAdReqDTO moduleAdDTO) throws Exception;
}
