package com.ai.bdex.dataexchange.aipcenter.dubbo.impl;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipProviderInfo;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipProviderServiceInfo;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipProviderInfoReqDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipProviderInfoRespDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipProviderServiceInfoReqDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipProviderServiceInfoRespDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipProviderServiceMgrRSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipProviderInfoSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipProviderServiceInfoSV;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.paas.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yx on 2017/5/12.
 */
@Service("iAipProviderServiceMgrRSV")
public class AipProviderServiceMgrRSVImpl implements IAipProviderServiceMgrRSV {

    private final static Logger log = LoggerFactory.getLogger(AipProviderServiceMgrRSVImpl.class);

    @Autowired
    private IAipProviderServiceInfoSV iAipProviderServiceInfoSV;

    @Autowired
    private IAipProviderInfoSV iAipProviderInfoSV;

    @Override
    public PageResponseDTO<AipProviderServiceInfoRespDTO> pagePServiceInfo(AipProviderServiceInfoReqDTO aipProviderServiceInfoReqDTO) throws Exception {

        PageResponseDTO<AipProviderServiceInfoRespDTO> pageResponseDTO = null;
        try {
            pageResponseDTO = iAipProviderServiceInfoSV.pagePServiceInfo(aipProviderServiceInfoReqDTO);
        }catch (Exception e){
            log.error("查询供应商服务分页列表异常：",e);
        }

        return pageResponseDTO;
    }

    @Override
    public AipProviderInfoRespDTO queryAipProviderInfoByProviderId(String providerId) throws Exception {
        AipProviderInfoRespDTO aipProviderInfoRespDTO = new AipProviderInfoRespDTO();
        try{
            AipProviderInfo aipProviderInfo = iAipProviderInfoSV.getAipProviderInfo(providerId);
            if (aipProviderInfo!=null){
                ObjectCopyUtil.copyObjValue(aipProviderInfo,aipProviderInfoRespDTO,null,false);
            }
        }catch (Exception e){
            log.error("查询供应商信息异常：",e);
        }

        return aipProviderInfoRespDTO;
    }

    @Override
    public AipProviderServiceInfoRespDTO queryPServiceByKey(String serviceId, String version) throws Exception {
        if (StringUtil.isBlank(serviceId) || StringUtil.isBlank(version)){
            throw new BusinessException("查询供应商服务信息异常，入参为空");
        }
        AipProviderServiceInfoRespDTO aipProviderServiceInfoRespDTO = new AipProviderServiceInfoRespDTO();
        try {
            AipProviderServiceInfo aipProviderServiceInfo = iAipProviderServiceInfoSV.queryAipProviderServiceInfoByKey(serviceId, version);
            if (aipProviderServiceInfo!=null){
                ObjectCopyUtil.copyObjValue(aipProviderServiceInfo,aipProviderServiceInfoRespDTO,null,false);
            }else{
                aipProviderServiceInfoRespDTO = null;
            }
        }catch (Exception e){
            log.error("查询供应商服务信息异常：",e);
        }
        return aipProviderServiceInfoRespDTO;
    }
    @Override
	public PageResponseDTO<AipProviderInfoRespDTO> pageAipProviderInfo(AipProviderInfoReqDTO aipProviderInfoReqDTO) throws Exception {

        PageResponseDTO<AipProviderInfoRespDTO> pageResponseDTO = new PageResponseDTO<AipProviderInfoRespDTO>();
        try {
            pageResponseDTO = iAipProviderInfoSV.pageAipProviderInfo(aipProviderInfoReqDTO);
        }catch (Exception e){
            log.error("查询服务商信息分页列表异常：",e);
        }

        return pageResponseDTO;
    }
	public void updateAipProviderInfo(AipProviderInfoReqDTO aipProviderInfoReqDTO) throws Exception{
	    if (StringUtil.isBlank(aipProviderInfoReqDTO.getProviderId())){
            throw new BusinessException("更新服务商信息异常，入参为空");
        }
	    try {
             iAipProviderInfoSV.updateAipProviderInfo(aipProviderInfoReqDTO);
        }catch (Exception e){
            log.error("更新服务商信息异常异常：",e);
        }
	}
	public String insertAipProviderInfo(AipProviderInfoReqDTO aipProviderInfoReqDTO) throws Exception{
	    if (aipProviderInfoReqDTO==null){
            throw new BusinessException("插入服务商信息异常，入参为空");
        }
	    String providerId="";
	    try {
	    	providerId=iAipProviderInfoSV.insertAipProviderInfo(aipProviderInfoReqDTO);
        }catch (Exception e){
            log.error("更新服务商信息异常异常：",e);
        }
	    return providerId;
	}
	/**
	 * 调整AIP服务供应商排序
	 * @param aipProviderInfoReqDTO
	 * @throws Exception
	 */
    public void modidyAipProviderInfoSort(AipProviderInfoReqDTO aipProviderInfoReqDTO) throws Exception{
    	 if (aipProviderInfoReqDTO.getProviderId()==null||aipProviderInfoReqDTO.getProviderSort()==null){
             throw new BusinessException("调整AIP服务供应商排序信息异常，providerId或providerSort为空");
         }
 	    try {
 	    	iAipProviderInfoSV.modidyAipProviderInfoSort(aipProviderInfoReqDTO);
         }catch (Exception e){
             log.error("更新服务商信息异常异常：",e);
         }
    }
}
