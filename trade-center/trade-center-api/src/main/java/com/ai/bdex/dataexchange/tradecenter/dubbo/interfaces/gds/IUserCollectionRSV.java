package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds;

import java.util.List;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.UserCollectionReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.UserCollectionRespDTO;
/**
 * 
 * Title: ECP <br>
 * Project Name:trade-center-api <br>
 * Description: <br>
 * Date:2017年5月8日上午11:25:04  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public interface IUserCollectionRSV {
    /**
     * 
     * queryUserCollectionList:(获取用户商品搜藏列表). <br/> 
     * 
     * @author gxq 
     * @param userCollectionReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<UserCollectionRespDTO> queryUserCollectionList(UserCollectionReqDTO userCollectionReqDTO) throws BusinessException;
    /**
     * 
     * insertUserCollection:(添加用户商品搜索列表). <br/> 
     * 
     * @author gxq 
     * @param userCollectionReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int insertUserCollection(UserCollectionReqDTO userCollectionReqDTO) throws BusinessException;
    /**
     * 
     * updateUserCollection:(更新用户商品收藏列表). <br/> 
     * 
     * @author gxq 
     * @param userCollectionReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int updateUserCollection(UserCollectionReqDTO userCollectionReqDTO) throws BusinessException;
    /**
     * 
     * deleteUserCollectionById:(根据条件删除记录). <br/> 
     * 
     * @author gxq 
     * @param colId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int deleteUserCollection(UserCollectionReqDTO userCollectionReqDTO) throws BusinessException;
    /**
     * 
     * queryUserCollection:(查询用户商品收藏列表). <br/> 
     * 
     * @author gxq 
     * @param userCollectionReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public UserCollectionRespDTO queryUserCollection(UserCollectionReqDTO userCollectionReqDTO) throws BusinessException;
    /**
     * 
     * count:(计数). <br/> 
     * 
     * @author gxq 
     * @param userCollectionReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long count(UserCollectionReqDTO userCollectionReqDTO) throws BusinessException;

    /**
     *
     * @param gdsInfoReqDTO
     * @return
     */
    public PageResponseDTO<UserCollectionRespDTO> queryUserCollectionPage(UserCollectionReqDTO userCollectionReqDTO) throws BusinessException;
    
    /**
    *   这里关联了表t_gds_info，为了取gdsName,gdsSubtitle,gdsPic,funIntroduction,status 。主要目的是用gdsName 来关联查询的哦
    * @param gdsInfoReqDTO
    * @return
    */
   public PageResponseDTO<UserCollectionRespDTO> queryUserCollectionPageExtends(UserCollectionReqDTO userCollectionReqDTO) throws BusinessException;
}

