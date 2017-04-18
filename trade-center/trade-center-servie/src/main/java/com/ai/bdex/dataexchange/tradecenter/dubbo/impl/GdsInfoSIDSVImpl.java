package com.ai.bdex.dataexchange.tradecenter.dubbo.impl;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfoReqVO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfoRespVO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.Gds.IGdsInfoSIDSVImpl;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfoSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * Created by yx on 2017/4/18.
 */
public class GdsInfoSIDSVImpl implements IGdsInfoSIDSVImpl {

    private static final Logger log = LoggerFactory.getLogger(GdsInfoSIDSVImpl.class);

    @Resource
    private IGdsInfoSV iGdsInfoSV;

    @Override
    public GdsInfoRespVO queryGdsInfoDetails(GdsInfoReqVO gdsInfoReqVO) throws Exception {
        GdsInfoRespVO gdsInfoRespVO = null;
        if (gdsInfoReqVO==null || gdsInfoReqVO.getGdsId()==null || gdsInfoReqVO.getGdsId().intValue()<=0){
            throw new Exception("查询商品详细信息异常，入参为空");
        }

        return null;
    }
}
