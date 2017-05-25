package com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds;

import java.util.List;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.UserFootPrintReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.UserFootPrintRespDTO;

public interface IUserFootPrintSV {

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
    
    /**
     *   这个扩展只为了 关联商品表，用gdsname 来查询记录而已。
     * @param gdsInfoReqDTO
     * @return
     */
    public PageResponseDTO<UserFootPrintRespDTO> queryUserFootPrintPageExtends(UserFootPrintReqDTO userFootPrintReqDTO) throws BusinessException;
    
    /**
     *   这个扩展只为了 更新商品浏览次数而已
     * @param gdsInfoReqDTO
     * @return
     */
    public int increaseSeeNum(UserFootPrintReqDTO userFootPrintReqDTO) throws BusinessException;
    
    /**
     *   这个扩展只为了 更新商品浏览次数而已
     * @param gdsInfoReqDTO
     * @return
     */
    public int reduceSeeNum(UserFootPrintReqDTO userFootPrintReqDTO) throws BusinessException;
}

