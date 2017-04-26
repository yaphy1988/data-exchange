package com.ai.bdex.dataexchange.tradecenter.service.interfaces.page;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageNewsInfo;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageNewsInfoDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageNewsInfoRespDTO;
 
public interface IPageNewsInfoSV {
	   public PageNewsInfo queryPageNewsInfoById(Integer PageNewsInfoid) throws Exception;

	   public PageResponseDTO<PageNewsInfoDTO> queryPageNewsInfoList(PageNewsInfoRespDTO exam) throws Exception;
}
