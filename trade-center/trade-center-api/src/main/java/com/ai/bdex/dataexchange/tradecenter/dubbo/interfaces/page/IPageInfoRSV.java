package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.page;
 
 
import java.util.List;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.*;

public interface    IPageInfoRSV {
    public  List<SortInfoRespDTO>  querySortInfos(SortInfoRespDTO sortInfoRespDTO) throws Exception;

    public List<PageInfoRespDTO> queryPageInfoList(PageInfoRespDTO sortInfoRespDTO) throws Exception;

    public PageInfoRespDTO queryPageInfoBYid(Integer PageInfoid) throws Exception;

    public List<PageHotSearchRespDTO> queryPageHotSearchList(PageHotSearchRespDTO pageHotSearchRespDTO) throws Exception;

    public List<PageHeaderNavRespDTO> queryPageHeaderNavList(PageHeaderNavRespDTO pageHeaderNavRespDTO) throws Exception;
    
    public List<PageModuleRespDTO> queryPageModuleList(PageModuleRespDTO pageModuleRespDTO) throws Exception;
    
    public List<PageModuleAdRespDTO> queryPageModuleAdList(PageModuleAdRespDTO pageModuleAdRespDTO) throws Exception;
    
    public PageResponseDTO<PageModuleGoodsRespDTO>  queryPageModuleGoodsList(PageModuleGoodsRespDTO pageModuleGoodsRespDTO) throws Exception;
    
    public List<PageModuleAdPropRespDTO> queryPageModuleAdPropList(PageModuleAdPropRespDTO pageModuleAdpropRespDTO) throws Exception;
    
    public int saveDataCustomizationRsv(DataCustomizationRespDTO dataCustomizationRespDTO) throws Exception;
    
    public List<SortContentRespDTO> querysortContenList(SortContentRespDTO sortContentRespDTO) throws Exception;
}
