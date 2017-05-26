package com.ai.bdex.dataexchange.tradecenter.dao.mapper;

import com.ai.bdex.dataexchange.tradecenter.dao.model.PicLib;

public interface PicLibExtendsMapper {
    /**
     * 
     * increasePicNum:(增加图片数量). <br/> 
     * 
     * @author gxq 
     * @param example
     * @return 
     * @since JDK 1.6
     */
    int increasePicNum(PicLib picLib);

    /**
     * 
     * reducePicNum:(减少图片数量). <br/> 
     * 
     * @author gxq 
     * @param example
     * @return 
     * @since JDK 1.6
     */
    int reducePicNum(PicLib picLib);
}

