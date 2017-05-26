package com.ai.bdex.dataexchange.tradecenter.dao.mapper;

import com.ai.bdex.dataexchange.tradecenter.dao.model.UserFootPrint;

public interface UserFootPrintUpdateExtendsMapper {
    /**
     * 
     * increasePicNum:(增加商品浏览次数). <br/> 
     * 
     * @author gxq 
     * @param example
     * @return 
     * @since JDK 1.6
     */
    int increaseSeeNum(UserFootPrint userFootPrint);

    /**
     * 
     * reducePicNum:(减少浏览次数). <br/> 
     * 
     * @author gxq 
     * @param example
     * @return 
     * @since JDK 1.6
     */
    int reduceSeeNum(UserFootPrint userFootPrint);
}

