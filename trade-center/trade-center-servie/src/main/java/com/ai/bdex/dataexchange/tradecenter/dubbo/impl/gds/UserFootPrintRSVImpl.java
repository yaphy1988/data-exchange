package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.gds;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.UserFootPrintReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.UserFootPrintRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IUserFootPrintRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IUserFootPrintSV;
import com.ai.bdex.dataexchange.util.StringUtil;
/**
 * 
 * Title: ECP <br>
 * Project Name:trade-center-servie <br>
 * Description: <br>
 * Date:2017年5月9日下午5:58:35  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@Service("userFootPrintRSV")
public class UserFootPrintRSVImpl implements IUserFootPrintRSV{
    private static final Logger logger = LoggerFactory.getLogger(UserFootPrintRSVImpl.class.getName());
    @Resource
    private IUserFootPrintSV iUserFootPrintSV;
    
    @Override
    public List<UserFootPrintRespDTO> queryUserFootPrintList(
            UserFootPrintReqDTO userFootPrintReqDTO) throws BusinessException {
        if(userFootPrintReqDTO == null){
            throw new BusinessException("入参userFootPrintReqDTO不能为null");
        }
        try {
            return  iUserFootPrintSV.queryUserFootPrintList(userFootPrintReqDTO);
        } catch (Exception e) {
            logger.error("查询商品浏览历史列表失败：",e);
            throw new BusinessException("查询商品浏览历史列表失败："+e.getMessage());
        }
    }

    @Override
    public int insertUserFootPrint(UserFootPrintReqDTO userFootPrintReqDTO)
            throws BusinessException {
        if(userFootPrintReqDTO == null){
            throw new BusinessException("入参userFootPrintReqDTO不能为null");
        }
        try {
            return  iUserFootPrintSV.insertUserFootPrint(userFootPrintReqDTO);
        } catch (Exception e) {
            logger.error("保存商品浏览历史列表失败：",e);
            throw new BusinessException("保存商品浏览历史列表失败："+e.getMessage());
        }
    }

    @Override
    public int updateUserFootPrint(UserFootPrintReqDTO userFootPrintReqDTO)
            throws BusinessException {
        if(userFootPrintReqDTO == null){
            throw new BusinessException("入参userFootPrintReqDTO不能为null");
        }
        try {
            return  iUserFootPrintSV.updateUserFootPrint(userFootPrintReqDTO);
        } catch (Exception e) {
            logger.error("更新存商品浏览历史列表失败：",e);
            throw new BusinessException("更新商品浏览历史列表失败："+e.getMessage());
        }
    }

    @Override
    public int deleteUserFootPrint(UserFootPrintReqDTO userFootPrintReqDTO)
            throws BusinessException {
        if(userFootPrintReqDTO == null){
            throw new BusinessException("入参userFootPrintReqDTO不能为null");
        }
        try {
            return  iUserFootPrintSV.deleteUserFootPrint(userFootPrintReqDTO);
        } catch (Exception e) {
            logger.error("删除存商品浏览历史列表失败：",e);
            throw new BusinessException("删除商品浏览历史列表失败："+e.getMessage());
        }
    }

    @Override
    public UserFootPrintRespDTO queryUserFootPrint(UserFootPrintReqDTO userFootPrintReqDTO)
            throws BusinessException {
        if(userFootPrintReqDTO == null){
            throw new BusinessException("入参userFootPrintReqDTO不能为null");
        }
        try {
            return  iUserFootPrintSV.queryUserFootPrint(userFootPrintReqDTO);
        } catch (Exception e) {
            logger.error("获取存商品浏览历史列表失败：",e);
            throw new BusinessException("获取商品浏览历史列表失败："+e.getMessage());
        }
    }

    @Override
    public long count(UserFootPrintReqDTO userFootPrintReqDTO) throws BusinessException {
        if(userFootPrintReqDTO == null){
            throw new BusinessException("入参userFootPrintReqDTO不能为null");
        }
        try {
            return  iUserFootPrintSV.count(userFootPrintReqDTO);
        } catch (Exception e) {
            logger.error("获取商品浏览历史计数失败：",e);
            throw new BusinessException("获取商品浏览历史技术失败："+e.getMessage());
        }
    }

    @Override
    public PageResponseDTO<UserFootPrintRespDTO> queryUserFootPrintPage(
            UserFootPrintReqDTO userFootPrintReqDTO) throws BusinessException {
        if(userFootPrintReqDTO == null){
            throw new BusinessException("入参userFootPrintReqDTO不能为null");
        }
        try {
            return  iUserFootPrintSV.queryUserFootPrintPage(userFootPrintReqDTO);
        } catch (Exception e) {
            logger.error("获取商品浏览历史分页列表失败：",e);
            throw new BusinessException("获取商品浏览历史分页列表失败："+e.getMessage());
        }
    }

    @Override
    public PageResponseDTO<UserFootPrintRespDTO> queryUserFootPrintPageExtends(
            UserFootPrintReqDTO userFootPrintReqDTO) throws BusinessException {
        if(userFootPrintReqDTO == null){
            throw new BusinessException("入参userFootPrintReqDTO不能为null");
        }
        try {
            return  iUserFootPrintSV.queryUserFootPrintPageExtends(userFootPrintReqDTO);
        } catch (Exception e) {
            logger.error("获取商品浏览历史分页列表失败：",e);
            throw new BusinessException("获取商品浏览历史分页列表失败："+e.getMessage());
        }
    }

    @Override
    public int increaseSeeNum(UserFootPrintReqDTO userFootPrintReqDTO) throws BusinessException {
        if(userFootPrintReqDTO == null){
            throw new BusinessException("入参userFootPrintReqDTO不能为null");
        }
        if(userFootPrintReqDTO.getGdsId()==null || userFootPrintReqDTO.getGdsId().intValue()<1){
            throw new BusinessException("入参gdsId不能为空");
        }
        if(StringUtil.isBlank(userFootPrintReqDTO.getStaffId())){
            throw new BusinessException("入参staffId不能为空");
        }
        try {
            return  iUserFootPrintSV.increaseSeeNum(userFootPrintReqDTO);
        } catch (Exception e) {
            logger.error("商品浏览记录更新失败：",e);
            throw new BusinessException("商品浏览记录更新失败："+e.getMessage());
        }
    }

    @Override
    public int reduceSeeNum(UserFootPrintReqDTO userFootPrintReqDTO) throws BusinessException {
        if(userFootPrintReqDTO == null){
            throw new BusinessException("入参userFootPrintReqDTO不能为null");
        }
        if(userFootPrintReqDTO.getGdsId()==null || userFootPrintReqDTO.getGdsId().intValue()<1){
            throw new BusinessException("入参gdsId不能为空");
        }
        if(StringUtil.isBlank(userFootPrintReqDTO.getStaffId())){
            throw new BusinessException("入参staffId不能为空");
        }
        try {
            return  iUserFootPrintSV.reduceSeeNum(userFootPrintReqDTO);
        } catch (Exception e) {
            logger.error("商品浏览记录更新失败：",e);
            throw new BusinessException("商品浏览记录更新失败："+e.getMessage());
        }
    }

}

