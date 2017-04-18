package com.ai.bdex.dataexchange.util;

import com.ai.bdex.dataexchange.common.dto.BaseResponseDTO;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yafei
 * @since 2016/11/22
 */
public class PageResponseFactory {
    private static Logger logger = LoggerFactory.getLogger(PageResponseFactory.class);

    /**
     * 把数据库中查询出来的bo列表转为dto并封装到分页PageResponseDTO中
     * @param pageInfo
     * @param clazz
     * @return
     * @author liangyi
     */
    public static <T extends BaseResponseDTO> PageResponseDTO<T> genPageResponse(PageInfo pageInfo, Class<?> clazz) {
        PageResponseDTO respDTO = new PageResponseDTO();
        List dtos = new ArrayList<>();
        BaseResponseDTO dto = null;
        try {
            for (Object bo : pageInfo.getList()) {
                dto = (BaseResponseDTO) Class.forName(clazz.getName()).newInstance();
                ObjectCopyUtil.copyObjValue(bo, dto, null, false);
                dtos.add(dto);
            }
            respDTO.setResult(dtos);
            respDTO.setCount(pageInfo.getTotal());
            respDTO.setPageCount(pageInfo.getPages());
            respDTO.setPageNo(pageInfo.getPageNum());
            respDTO.setPageSize(pageInfo.getPageSize());
        } catch (InstantiationException e) {
            logger.error("InstantiationException :",e);
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException :",e);
        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException :",e);
        }
        return respDTO;
    }
    
    /**
     * 将分页信息（除了结果list）封装到分页PageResponseDTO中
     * @param pageInfo
     * @return
     * @author liangyi
     */
    public static <T extends BaseResponseDTO> PageResponseDTO<T> genPageResponseWithoutResult(PageInfo pageInfo) {
        PageResponseDTO respDTO = new PageResponseDTO();
        respDTO.setCount(pageInfo.getTotal());
        respDTO.setPageCount(pageInfo.getPages());
        respDTO.setPageNo(pageInfo.getPageNum());
        respDTO.setPageSize(pageInfo.getPageSize());
        return respDTO;
    }
}
