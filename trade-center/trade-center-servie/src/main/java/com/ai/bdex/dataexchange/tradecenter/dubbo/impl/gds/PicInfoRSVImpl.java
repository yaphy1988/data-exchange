package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.gds;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.PicInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.PicInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IPicInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IPicInfoSV;
/**
 * 
 * Title: ECP <br>
 * Project Name:trade-center-servie <br>
 * Description: <br>
 * Date:2017年5月10日上午10:51:38  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@Service("picInfoRSV")
public class PicInfoRSVImpl implements IPicInfoRSV{
    private static final Logger logger = LoggerFactory.getLogger(PicInfoRSVImpl.class.getName());
    @Resource
    private IPicInfoSV iPicInfoSV;
    
    @Override
    public List<PicInfoRespDTO> queryPicInfoList(PicInfoReqDTO picInfoReqDTO)
            throws BusinessException {
        if(picInfoReqDTO == null){
            throw new BusinessException("入参picInfoReqDTO不能为null");
        }
        try {
            return  iPicInfoSV.queryPicInfoList(picInfoReqDTO);
        } catch (Exception e) {
            logger.error("查询图片列表失败：",e);
            throw new BusinessException("查询图片列表失败："+e.getMessage());
        }
    }

    @Override
    public int insertPicInfo(PicInfoReqDTO picInfoReqDTO) throws BusinessException {
        if(picInfoReqDTO == null){
            throw new BusinessException("入参picInfoReqDTO不能为null");
        }
        try {
            return  iPicInfoSV.insertPicInfo(picInfoReqDTO);
        } catch (Exception e) {
            logger.error("保存图片失败：",e);
            throw new BusinessException("保存图片失败："+e.getMessage());
        }
    }

    @Override
    public int updatePicInfo(PicInfoReqDTO picInfoReqDTO) throws BusinessException {
        if(picInfoReqDTO == null){
            throw new BusinessException("入参picInfoReqDTO不能为null");
        }
        try {
            return  iPicInfoSV.updatePicInfo(picInfoReqDTO);
        } catch (Exception e) {
            logger.error("更新图片失败：",e);
            throw new BusinessException("更新图片失败："+e.getMessage());
        }
    }

    @Override
    public int deletePicInfo(PicInfoReqDTO picInfoReqDTO) throws BusinessException {
        if(picInfoReqDTO == null){
            throw new BusinessException("入参picInfoReqDTO不能为null");
        }
        try {
            return  iPicInfoSV.deletePicInfo(picInfoReqDTO);
        } catch (Exception e) {
            logger.error("删除图片失败：",e);
            throw new BusinessException("删除图片失败："+e.getMessage());
        }
    }

    @Override
    public PicInfoRespDTO queryPicInfo(PicInfoReqDTO picInfoReqDTO) throws BusinessException {
        if(picInfoReqDTO == null){
            throw new BusinessException("入参picInfoReqDTO不能为null");
        }
        try {
            return  iPicInfoSV.queryPicInfo(picInfoReqDTO);
        } catch (Exception e) {
            logger.error("查询图片失败：",e);
            throw new BusinessException("查询图片失败："+e.getMessage());
        }
    }

    @Override
    public long count(PicInfoReqDTO picInfoReqDTO) throws BusinessException {
        if(picInfoReqDTO == null){
            throw new BusinessException("入参picInfoReqDTO不能为null");
        }
        try {
            return  iPicInfoSV.count(picInfoReqDTO);
        } catch (Exception e) {
            logger.error("图片计数失败：",e);
            throw new BusinessException("图片计数失败："+e.getMessage());
        }
    }

    @Override
    public PageResponseDTO<PicInfoRespDTO> queryPicInfoPage(PicInfoReqDTO picInfoReqDTO)
            throws BusinessException {
        if(picInfoReqDTO == null){
            throw new BusinessException("入参picInfoReqDTO不能为null");
        }
        try {
            return  iPicInfoSV.queryPicInfoPage(picInfoReqDTO);
        } catch (Exception e) {
            logger.error("查询图片列表失败：",e);
            throw new BusinessException("查询图片列表失败："+e.getMessage());
        }
    }

}

