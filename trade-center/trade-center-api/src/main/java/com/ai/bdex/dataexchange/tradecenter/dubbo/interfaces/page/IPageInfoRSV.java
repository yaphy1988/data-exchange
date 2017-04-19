package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.page;
 
 
import java.util.List;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Page.PageHeaderNavRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Page.PageHotSearchRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Page.PageInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Page.SortInfoRespDTO;
 
public interface    IPageInfoRSV {
    public  List<SortInfoRespDTO>  querySortInfos(SortInfoRespDTO sortInfoRespDTO) throws Exception;

    public List<PageInfoRespDTO> queryPageInfoList(PageInfoRespDTO sortInfoRespDTO) throws Exception;

    public PageInfoRespDTO queryPageInfoBYid(Integer PageInfoid) throws Exception;

    public List<PageHotSearchRespDTO> queryPageHotSearchNavList(PageHotSearchRespDTO pageHotSearchRespDTO) throws Exception;

    public List<PageHeaderNavRespDTO> queryPageHeaderNavList(PageHeaderNavRespDTO PageHeaderNavRespDTO) throws Exception;
}
