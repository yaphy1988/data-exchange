package com.ai.bdex.dataexchange.apigateway.service.interfaces;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipServiceSpring;

public interface IAipServiceSpringSV {
	AipServiceSpring getAipServiceSpringByKey(String serviceId,String version);
}
