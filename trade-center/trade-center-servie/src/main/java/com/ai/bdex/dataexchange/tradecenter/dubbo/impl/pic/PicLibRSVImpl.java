package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.pic;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.pic.PicLibReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.pic.PicLibRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.pic.IPicLibRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.pic.IPicLibSV;
/**
 * 
 * Title: ECP <br>
 * Project Name:trade-center-servie <br>
 * Description: <br>
 * Date:2017年5月10日上午10:52:09  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@Service("picLibRSV")
public class PicLibRSVImpl implements IPicLibRSV{
    private static final Logger logger = LoggerFactory.getLogger(PicLibRSVImpl.class.getName());
    @Resource
    private IPicLibSV iPicLibSV;
    @Override
    public List<PicLibRespDTO> queryPicLibList(PicLibReqDTO picLibReqDTO) throws BusinessException {
        if(picLibReqDTO == null){
            throw new BusinessException("入参picLibReqDTO不能为null");
        }
        try {
            return  iPicLibSV.queryPicLibList(picLibReqDTO);
        } catch (Exception e) {
            logger.error("查询图片库列表失败：",e);
            throw new BusinessException("查询图片库列表失败："+e.getMessage());
        }
    }

    @Override
    public int insertPicLib(PicLibReqDTO picLibReqDTO) throws BusinessException {
        if(picLibReqDTO == null){
            throw new BusinessException("入参picLibReqDTO不能为null");
        }
        try {
            return  iPicLibSV.insertPicLib(picLibReqDTO);
        } catch (Exception e) {
            logger.error("查询图片库列表失败：",e);
            throw new BusinessException("查询图片库列表失败："+e.getMessage());
        }
    }

    @Override
    public int updatePicLib(PicLibReqDTO picLibReqDTO) throws BusinessException {
        if(picLibReqDTO == null){
            throw new BusinessException("入参picLibReqDTO不能为null");
        }
        try {
            return  iPicLibSV.updatePicLib(picLibReqDTO);
        } catch (Exception e) {
            logger.error("查询图片库列表失败：",e);
            throw new BusinessException("查询图片库列表失败："+e.getMessage());
        }
    }

    @Override
    public int deletePicLib(PicLibReqDTO picLibReqDTO) throws BusinessException {
        if(picLibReqDTO == null){
            throw new BusinessException("入参picLibReqDTO不能为null");
        }
        try {
            return  iPicLibSV.deletePicLib(picLibReqDTO);
        } catch (Exception e) {
            logger.error("查询图片库列表失败：",e);
            throw new BusinessException("查询图片库列表失败："+e.getMessage());
        }
    }

    @Override
    public PicLibRespDTO queryPicLib(PicLibReqDTO picLibReqDTO) throws BusinessException {
        if(picLibReqDTO == null){
            throw new BusinessException("入参picLibReqDTO不能为null");
        }
        try {
            return  iPicLibSV.queryPicLib(picLibReqDTO);
        } catch (Exception e) {
            logger.error("查询图片库列表失败：",e);
            throw new BusinessException("查询图片库列表失败："+e.getMessage());
        }
    }

    @Override
    public long count(PicLibReqDTO picLibReqDTO) throws BusinessException {
        if(picLibReqDTO == null){
            throw new BusinessException("入参picLibReqDTO不能为null");
        }
        try {
            return  iPicLibSV.count(picLibReqDTO);
        } catch (Exception e) {
            logger.error("查询图片库列表失败：",e);
            throw new BusinessException("查询图片库列表失败："+e.getMessage());
        }
    }

    @Override
    public PageResponseDTO<PicLibRespDTO> queryPicLibPage(PicLibReqDTO picLibReqDTO)
            throws BusinessException {
        if(picLibReqDTO == null){
            throw new BusinessException("入参picLibReqDTO不能为null");
        }
        try {
            return  iPicLibSV.queryPicLibPage(picLibReqDTO);
        } catch (Exception e) {
            logger.error("查询图片库列表失败：",e);
            throw new BusinessException("查询图片库列表失败："+e.getMessage());
        }
    }

}

