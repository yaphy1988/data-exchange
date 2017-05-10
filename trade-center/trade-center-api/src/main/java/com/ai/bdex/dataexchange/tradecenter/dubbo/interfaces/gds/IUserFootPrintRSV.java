package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds;

import java.util.List;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.UserFootPrintReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.UserFootPrintRespDTO;
/**
 * 
 * Title: ECP <br>
 * Project Name:trade-center-api <br>
 * Description: <br>
 * Date:2017年5月9日下午5:57:52  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public interface IUserFootPrintRSV {
    /**
     * 
     * queryUserFootPrintList:(获取用户商品浏览历史列表). <br/> 
     * 
     * @author gxq 
     * @param UserFootPrintReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<UserFootPrintRespDTO> queryUserFootPrintList(UserFootPrintReqDTO userFootPrintReqDTO) throws BusinessException;
    /**
     * 
     * insertUserFootPrint:(添加用户商品浏览历史列表). <br/> 
     * 
     * @author gxq 
     * @param UserFootPrintReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int insertUserFootPrint(UserFootPrintReqDTO userFootPrintReqDTO) throws BusinessException;
    /**
     * 
     * updateUserFootPrint:(更新用户商品浏览历史列表). <br/> 
     * 
     * @author gxq 
     * @param UserFootPrintReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int updateUserFootPrint(UserFootPrintReqDTO userFootPrintReqDTO) throws BusinessException;
    /**
     * 
     * deleteUserFootPrintById:(根据条件删除记录). <br/> 
     * 
     * @author gxq 
     * @param colId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int deleteUserFootPrint(UserFootPrintReqDTO userFootPrintReqDTO) throws BusinessException;
    /**
     * 
     * queryUserFootPrint:(查询用户商品浏览历史列表). <br/> 
     * 
     * @author gxq 
     * @param UserFootPrintReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public UserFootPrintRespDTO queryUserFootPrint(UserFootPrintReqDTO userFootPrintReqDTO) throws BusinessException;
    /**
     * 
     * count:(计数). <br/> 
     * 
     * @author gxq 
     * @param UserFootPrintReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long count(UserFootPrintReqDTO userFootPrintReqDTO) throws BusinessException;

    /**
     *
     * @param gdsInfoReqDTO
     * @return
     */
    public PageResponseDTO<UserFootPrintRespDTO> queryUserFootPrintPage(UserFootPrintReqDTO userFootPrintReqDTO) throws BusinessException;
}

