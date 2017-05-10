package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds;

import java.util.List;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.PicInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.PicInfoRespDTO;

public interface IPicInfoRSV {
    /**
     * 
     * queryPicInfoList:(获取图片列表). <br/> 
     * 
     * @author gxq 
     * @param PicInfoReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<PicInfoRespDTO> queryPicInfoList(PicInfoReqDTO picInfoReqDTO) throws BusinessException;
    /**
     * 
     * insertPicInfo:(添加图片列表). <br/> 
     * 
     * @author gxq 
     * @param PicInfoReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int insertPicInfo(PicInfoReqDTO picInfoReqDTO) throws BusinessException;
    /**
     * 
     * updatePicInfo:(更新图片列表). <br/> 
     * 
     * @author gxq 
     * @param PicInfoReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int updatePicInfo(PicInfoReqDTO picInfoReqDTO) throws BusinessException;
    /**
     * 
     * deletePicInfoById:(根据条件删除记录). <br/> 
     * 
     * @author gxq 
     * @param colId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int deletePicInfo(PicInfoReqDTO picInfoReqDTO) throws BusinessException;
    /**
     * 
     * queryPicInfo:(查询图片). <br/> 
     * 
     * @author gxq 
     * @param PicInfoReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PicInfoRespDTO queryPicInfo(PicInfoReqDTO picInfoReqDTO) throws BusinessException;
    /**
     * 
     * count:(计数). <br/> 
     * 
     * @author gxq 
     * @param PicInfoReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long count(PicInfoReqDTO picInfoReqDTO) throws BusinessException;

    /**
     *
     * @param gdsInfoReqDTO
     * @return
     */
    public PageResponseDTO<PicInfoRespDTO> queryPicInfoPage(PicInfoReqDTO picInfoReqDTO) throws BusinessException;
}

