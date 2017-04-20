package com.ai.bdex.dataexchange.tradecenter.service.interfaces.page;

import com.ai.bdex.dataexchange.tradecenter.dao.model.DataCustomization;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Page.DataCustomizationRespDTO;
  
public interface IDataCustomizationSV {
	   public DataCustomization queryDATAById(Integer dcza_id) throws Exception;
	   public int saveDataCustomization(DataCustomizationRespDTO dataCustomizationRespDTO) throws Exception;
}
