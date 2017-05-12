package com.ai.bdex.dataexchange.aipcenter.dubbo.impl;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInParaDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInfoReqDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceOutParaDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipServiceManagerRSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipServiceInParaSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipServiceInfoSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipServiceOutParaSV;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.paas.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yx on 2017/5/11.
 */
@Service("iAipServiceManagerRSV")
public class AipServiceManagerRSVImpl implements IAipServiceManagerRSV {

    private final static Logger log = LoggerFactory.getLogger(AipServiceManagerRSVImpl.class);

    @Autowired
    private IAipServiceInfoSV iAipServiceInfoSV;
    @Autowired
    private IAipServiceInParaSV iAipServiceInParaSV;
    @Autowired
    private IAipServiceOutParaSV iAipServiceOutParaSV;


    @Override
    public String insertAipService(AipServiceInfoReqDTO aipServiceInfoReqDTO) throws Exception {
        if (aipServiceInfoReqDTO == null){
            throw new BusinessException("插入aip服务基本信息异常，入参为空");
        }
        String serviceId = "";
        try{
            serviceId = iAipServiceInfoSV.insertAipService(aipServiceInfoReqDTO);
        }catch (Exception e){
            log.error("插入aip服务基本信息异常：",e);
            throw new Exception("插入aip服务基本信息异常：",e);
        }
        return serviceId;
    }

    @Override
    public void updateAipServiceByServiceId(AipServiceInfoReqDTO aipServiceInfoReqDTO) throws Exception {
        if (aipServiceInfoReqDTO==null || !StringUtil.isBlank(aipServiceInfoReqDTO.getServiceId())){
            throw new BusinessException("更新aip服务基本信息异常，入参为空");
        }
        try {
            iAipServiceInfoSV.updateAipServiceByServiceId(aipServiceInfoReqDTO);
        }catch (Exception e){
            log.error("更新aip服务基本信息异常：",e);
            throw new Exception("更新aip服务基本信息异常：",e);
        }
    }

    @Override
    public String insertInPara(AipServiceInParaDTO aipServiceInParaDTO) throws Exception {
        if (aipServiceInParaDTO==null){
            throw new BusinessException("插入aip入参信息列表异常，入参为空");
        }
        String inputId = "";
        try {
            inputId = iAipServiceInParaSV.insertInPara(aipServiceInParaDTO);
        }catch (Exception e){
            log.error("插入aip入参信息列表异常：",e);
            throw new Exception("插入aip入参信息列表异常：",e);
        }
        return inputId;
    }

    @Override
    public void updateInParaByServiceId(AipServiceInParaDTO aipServiceInParaDTO) throws Exception {
        if (aipServiceInParaDTO==null || aipServiceInParaDTO.getServiceId()==null){
            throw new BusinessException("更新aip入参信息列表异常，入参为空");
        }
        try{
            iAipServiceInParaSV.updateInParaByServiceId(aipServiceInParaDTO);
        }catch (Exception e){
            log.error("更新aip入参信息列表异常：",e);
            throw new Exception("更新aip入参信息列表异常：",e);
        }
    }

    @Override
    public String insertOutPara(AipServiceOutParaDTO aipServiceOutParaDTO) throws Exception {
        if (aipServiceOutParaDTO==null){
            throw new BusinessException("插入aip出参信息列表异常，入参为空");
        }
        String outputId = "";
        try {
            outputId = iAipServiceOutParaSV.insertOutPara(aipServiceOutParaDTO);
        }catch (Exception e){
            log.error("插入aip出参信息列表异常：",e);
            throw new Exception("插入aip出参信息列表异常：",e);
        }
        return outputId;
    }

    @Override
    public void updateOutParaByServiceId(AipServiceOutParaDTO aipServiceOutParaDTO) throws Exception {
        if (aipServiceOutParaDTO==null || aipServiceOutParaDTO.getServiceId()==null){
            throw new BusinessException("更新aip出参信息列表异常，入参为空");
        }
        try{
            iAipServiceOutParaSV.updateOutParaByServiceId(aipServiceOutParaDTO);
        }catch (Exception e){
            log.error("更新aip出参信息列表异常：",e);
            throw new Exception("更新aip出参信息列表异常：",e);
        }
    }
}
