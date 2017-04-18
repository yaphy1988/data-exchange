package com.ai.bdex.dataexchange.tradecenter.service.interfaces.page;

import com.ai.bdex.dataexchange.tradecenter.dao.model.DataCustomization;
 
public interface IDataCustomizationSV {
	   public DataCustomization queryDATAById(Integer dcza_id) throws Exception;
}
