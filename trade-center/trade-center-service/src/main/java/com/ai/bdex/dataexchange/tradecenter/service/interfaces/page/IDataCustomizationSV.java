package com.ai.bdex.dataexchange.tradecenter.service.interfaces.page;

import com.ai.bdex.dataexchange.tradecenter.dao.model.DataCustomization;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.DataCustomizationReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.DataCustomizationRespDTO;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
public interface IDataCustomizationSV {
	public DataCustomization queryDATAById(Integer dcza_id) throws Exception;
	public int saveDataCustomization(DataCustomizationRespDTO dataCustomizationRespDTO) throws Exception;
	public int updateDataCustomizationStatus(DataCustomizationReqDTO DataCustomizationReqDTO) throws Exception;
	public PageResponseDTO<DataCustomizationRespDTO> queryDataCustomizationInfo(DataCustomizationReqDTO dataCustomizationReqDTO)
			throws Exception ;
}
