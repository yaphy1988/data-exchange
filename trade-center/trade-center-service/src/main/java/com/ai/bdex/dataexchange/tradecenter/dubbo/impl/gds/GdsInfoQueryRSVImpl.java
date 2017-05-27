package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.gds;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoQueryRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfoQuerySV;

@Service("gdsInfoQueryRSV")
public class GdsInfoQueryRSVImpl implements IGdsInfoQueryRSV{
    private static final Logger logger = LoggerFactory.getLogger(GdsInfoQueryRSVImpl.class.getName());
    @Resource
    private IGdsInfoQuerySV iGdsInfoQuerySV;
    /**
     * add gongxq
     * TODO 简单描述该方法的实现功能（获取商品列表信息 ）. 
     * @see com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoQueryRSV#queryGdsInfoListPage(com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO)
     */
    @Override
    public PageResponseDTO<GdsInfoRespDTO> queryGdsInfoListPage(GdsInfoReqDTO gdsInfoReqDTO)
            throws Exception {
        return iGdsInfoQuerySV.queryGdsInfoListPage(gdsInfoReqDTO);
    }

}

