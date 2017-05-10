package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.pic;

import java.util.List;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.pic.PicLibReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.pic.PicLibRespDTO;

public interface IPicLibRSV {
    /**
     * 
     * queryPicLibList:(获取图片库列表). <br/> 
     * 
     * @author gxq 
     * @param PicLibReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<PicLibRespDTO> queryPicLibList(PicLibReqDTO picLibReqDTO) throws BusinessException;
    /**
     * 
     * insertPicLib:(添加图片库). <br/> 
     * 
     * @author gxq 
     * @param PicLibReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int insertPicLib(PicLibReqDTO picLibReqDTO) throws BusinessException;
    /**
     * 
     * updatePicLib:(更新图片库). <br/> 
     * 
     * @author gxq 
     * @param PicLibReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int updatePicLib(PicLibReqDTO picLibReqDTO) throws BusinessException;
    /**
     * 
     * deletePicLibById:(根据条件删除记录). <br/> 
     * 
     * @author gxq 
     * @param colId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int deletePicLib(PicLibReqDTO picLibReqDTO) throws BusinessException;
    /**
     * 
     * queryPicLib:(查询图片库). <br/> 
     * 
     * @author gxq 
     * @param PicLibReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PicLibRespDTO queryPicLib(PicLibReqDTO picLibReqDTO) throws BusinessException;
    /**
     * 
     * count:(计数). <br/> 
     * 
     * @author gxq 
     * @param PicLibReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long count(PicLibReqDTO picLibReqDTO) throws BusinessException;

    /**
     *
     * @param gdsInfoReqDTO
     * @return
     */
    public PageResponseDTO<PicLibRespDTO> queryPicLibPage(PicLibReqDTO picLibReqDTO) throws BusinessException;
}

