package com.ai.bdex.dataexchange.apigateway.service.interfaces;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipApiData;

public interface IAipApiDataSV {
	AipApiData getAipApiDataByKey(String serviceId,String versionId,String requestMd5);
	int insertAipApiData(AipApiData record);
}
