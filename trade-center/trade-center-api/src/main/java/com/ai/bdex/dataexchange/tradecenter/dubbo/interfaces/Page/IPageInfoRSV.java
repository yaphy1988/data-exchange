package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.Page;
 
 
import java.util.List;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Page.SortInfoRespDTO;
 
public interface    IPageInfoRSV {
    public  List<SortInfoRespDTO>  querySortInfos(SortInfoRespDTO sortInfoRespDTO) throws Exception;
}
