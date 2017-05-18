package com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.*;

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

    /**
     * 根据serviceId和version查询所有有效的错误代码信息
     * @param serviceId
     * @param version
     * @return
     * @throws Exception
     */
    public List<AipServiceErrorInfoDTO> queryServiceErrorInfoList(String serviceId,String version) throws Exception;

    /**
     * 批量插入aip服务错误代码信息
     * @param aipServiceErrorInfoReqDTOList
     * @return
     * @throws Exception
     */
    public String insertErrorInfoBatch(List<AipServiceErrorInfoReqDTO> aipServiceErrorInfoReqDTOList) throws Exception;

    /**
     * 根据serviceId和version更新服务错误代码信息
     * @param aipServiceErrorInfoReqDTO
     * @throws Exception
     */
    public void updateErrorInfoByServiceIdAndVersion(AipServiceErrorInfoReqDTO aipServiceErrorInfoReqDTO) throws Exception;

    /**
     * 根据serviceId和version查询示例代码列表
     * @param serviceId
     * @param version
     * @return
     * @throws Exception
     */
    public List<AipServiceCodeInfoDTO> queryAipServiceCodeList(String serviceId,String version) throws Exception;

    /**
     * 批量插入示例代码信息
     * @param aipServiceCodeInfoReqDTOList
     * @return
     * @throws Exception
     */
    public String insertServiceCodeBatch(List<AipServiceCodeInfoReqDTO> aipServiceCodeInfoReqDTOList) throws Exception;

    /**
     * 根据serviceId和version更新示例代码信息
     * @param aipServiceCodeInfoReqDTO
     * @return
     * @throws Exception
     */
    public void updateServiceCodeByServiceIdAndVersion(AipServiceCodeInfoReqDTO aipServiceCodeInfoReqDTO) throws Exception;

}
