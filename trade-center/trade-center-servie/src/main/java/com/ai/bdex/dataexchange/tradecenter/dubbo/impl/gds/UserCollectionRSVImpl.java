package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.gds;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.UserCollectionReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.UserCollectionRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IUserCollectionRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IUserCollectionSV;
/**
 * 
 * Title: ECP <br>
 * Project Name:trade-center-servie <br>
 * Description: <br>
 * Date:2017年5月8日上午11:25:36  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@Service("userCollectionRSV")
public class UserCollectionRSVImpl implements IUserCollectionRSV{
    private static final Logger logger = LoggerFactory.getLogger(UserCollectionRSVImpl.class.getName());
    
    @Resource
    private IUserCollectionSV iUserCollectionSV;
    
    @Override
    public List<UserCollectionRespDTO> queryUserCollectionList(
            UserCollectionReqDTO userCollectionReqDTO) throws BusinessException {
        if(userCollectionReqDTO == null){
            throw new BusinessException("入参userCollectionReqDTO不能为null");
        }
        try {
            return  iUserCollectionSV.queryUserCollectionList(userCollectionReqDTO);
        } catch (Exception e) {
            logger.error("查询商品收藏列表失败：",e);
            throw new BusinessException("查询商品收藏列表失败："+e.getMessage());
        }
    }

    @Override
    public int insertUserCollection(UserCollectionReqDTO userCollectionReqDTO)
            throws BusinessException {
        if(userCollectionReqDTO == null){
            throw new BusinessException("入参userCollectionReqDTO不能为null");
        }
        try {
            return iUserCollectionSV.insertUserCollection(userCollectionReqDTO);
        } catch (Exception e) {
            logger.error("保存商品收藏列表失败：",e);
            throw new BusinessException("保存商品收藏列表失败："+e.getMessage());
        }
    }

    @Override
    public int updateUserCollection(UserCollectionReqDTO userCollectionReqDTO)
            throws BusinessException {
        if(userCollectionReqDTO == null){
            throw new BusinessException("入参userCollectionReqDTO不能为null");
        }
        try {
            return iUserCollectionSV.updateUserCollection(userCollectionReqDTO);
        } catch (Exception e) {
            logger.error("更新商品收藏列表失败：",e);
            throw new BusinessException("更新商品收藏列表失败："+e.getMessage());
        }
    }

    @Override
    public int deleteUserCollection(UserCollectionReqDTO userCollectionReqDTO)
            throws BusinessException {
        if(userCollectionReqDTO == null){
            throw new BusinessException("入参userCollectionReqDTO不能为null");
        }
        try {
            return iUserCollectionSV.deleteUserCollection(userCollectionReqDTO);
        } catch (Exception e) {
            logger.error("删除商品收藏列表失败：",e);
            throw new BusinessException("删除商品收藏列表失败："+e.getMessage());
        }
    }

    @Override
    public UserCollectionRespDTO queryUserCollection(UserCollectionReqDTO userCollectionReqDTO)
            throws BusinessException {
        if(userCollectionReqDTO == null){
            throw new BusinessException("入参userCollectionReqDTO不能为null");
        }
        try {
            return iUserCollectionSV.queryUserCollection(userCollectionReqDTO);
        } catch (Exception e) {
            logger.error("查询商品收藏列表失败：",e);
            throw new BusinessException("查询商品收藏列表失败："+e.getMessage());
        }
    }

    @Override
    public long count(UserCollectionReqDTO userCollectionReqDTO) throws BusinessException {
        if(userCollectionReqDTO == null){
            throw new BusinessException("入参userCollectionReqDTO不能为null");
        }
        try {
            return iUserCollectionSV.count(userCollectionReqDTO);
        } catch (Exception e) {
            logger.error("统计商品收藏列表失败：",e);
            throw new BusinessException("统计商品收藏列表失败："+e.getMessage());
        }
    }

    @Override
    public PageResponseDTO<UserCollectionRespDTO> queryUserCollectionPage(
            UserCollectionReqDTO userCollectionReqDTO) throws BusinessException {
        if(userCollectionReqDTO == null){
            throw new BusinessException("入参userCollectionReqDTO不能为null");
        }
        try {
            return iUserCollectionSV.queryUserCollectionPage(userCollectionReqDTO);
        } catch (Exception e) {
            logger.error("分页查询商品收藏列表失败：",e);
            throw new BusinessException("分页查询商品收藏列表失败："+e.getMessage());
        }
    }

}

