package com.ai.bdex.dataexchange.apigateway.cfca.service.interfaces;

import java.util.Map;

import com.ai.bdex.dataexchange.apigateway.dubbo.dto.AipPServiceUsedLogDTO;

public interface ICFCATransactionSV {
	String applyKey(String lastKey,AipPServiceUsedLogDTO dto)throws Exception;
	Map  getResult(String serviceId,String version,Map<String,String> map)throws Exception;
}
