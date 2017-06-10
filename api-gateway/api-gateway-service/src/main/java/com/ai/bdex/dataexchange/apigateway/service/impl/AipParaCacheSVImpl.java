package com.ai.bdex.dataexchange.apigateway.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipParaCfg;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipParaSimple;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.APIConstants;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipParaCfgSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipParaSimpleSV;
import com.ai.paas.util.CacheUtil;
import com.ai.paas.utils.CollectionUtil;

@Service("gatewayAipParaCache")
public class AipParaCacheSVImpl implements InitializingBean{
	private static final Logger log = LoggerFactory.getLogger(AipParaCacheSVImpl.class);
	@Autowired
	private IAipParaCfgSV gatewayAipParaCfgSV;
	@Autowired
	private IAipParaSimpleSV gatewayAipParaSimpleSV;

	@Override
	public void afterPropertiesSet() throws Exception {		
		initAipCach();
	}
	
	private void initAipCach(){
		try{
			//获取需要缓存的配置
			List<AipParaCfg> paraList=gatewayAipParaCfgSV.getValidAipParaCfg();
			if(!CollectionUtil.isEmpty(paraList)){
				for(AipParaCfg paraCfg:paraList){
					String paraCode=paraCfg.getParaCode();
					String paraType=paraCfg.getParaType();
					String paraLinkKey=paraCfg.getParaLinkKey();
					//现只缓存simple类型的
					if(APIConstants.AipCache.AIP_PARA_SIMPLE_TYPE.equals(paraType)){
						List<AipParaSimple> simpleList= gatewayAipParaSimpleSV.getValidAipParaSimpleListByLinkKey(paraLinkKey);
						if(!CollectionUtil.isEmpty(simpleList)){
//							Map<String,String> cacheMap=new HashMap<String,String>();
							String mapCacheKey=APIConstants.AipCache.AIP_CACHE_NAME_PREFIX+paraCode;
							for(AipParaSimple simple:simpleList){
								String spCodeKey=mapCacheKey+"_"+simple.getSpCode();
								if(CacheUtil.exists(spCodeKey)){
									CacheUtil.delItem(spCodeKey);
								}
								CacheUtil.addItem(spCodeKey, simple.getSpValue());
							}
//							//扔进缓存							
//							if(CacheUtil.exists(mapCacheKey)){
//								CacheUtil.delItem(mapCacheKey);
//							}
//							CacheUtil.addMap(mapCacheKey, cacheMap);						
						}
					}
				}
			}
		}catch(Exception e){
			log.error("init cache failed", e);
		}
	}
	
}
