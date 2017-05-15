package com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInParaDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInfoReqDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceOutParaDTO;

import java.util.List;

/**
 * Created by yx on 2017/5/11.
 */
public interface IAipServiceManagerRSV {

    /**
     * 插入aip服务基本信息，需传version版本号
     * @param aipServiceInfoReqDTO
     * @return
     * @throws Exception
     */
    String insertAipService(AipServiceInfoReqDTO aipServiceInfoReqDTO) throws Exception;

    /**
     * 更新aip服务信息，根据serviceId更新
     * @param aipServiceInfoReqDTO
     * @throws Exception
     */
    void updateAipServiceByServiceId(AipServiceInfoReqDTO aipServiceInfoReqDTO) throws Exception;

    /**
     * 插入aip入参信息列表，需传version
     * @param aipServiceInParaDTO
     * @return
     * @throws Exception
     */
    String insertInPara(AipServiceInParaDTO aipServiceInParaDTO) throws Exception;


    /**
     * 根据serviceId和version更新所有入参
     * @param aipServiceInParaDTO
     * @throws Exception
     */
    void updateInParaByServiceIdAndVersion(AipServiceInParaDTO aipServiceInParaDTO) throws Exception;

    /**
     * 插入aip入参信息列表，需传version
     * @param aipServiceOutParaDTO
     * @return
     * @throws Exception
     */
    String insertOutPara(AipServiceOutParaDTO aipServiceOutParaDTO) throws Exception;


    /**
     * 根据serviceId和version更新所有出参
     * @param aipServiceOutParaDTO
     * @throws Exception
     */
    void updateOutParaByServiceIdAndVersion(AipServiceOutParaDTO aipServiceOutParaDTO) throws Exception;

    public List<AipServiceInParaDTO> queryServiceInParaList(String serviceId, String version) throws Exception;

    public List<AipServiceOutParaDTO> queryServiceOutParaList(String serviceId,String version) throws Exception;

}
