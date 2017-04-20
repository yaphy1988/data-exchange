package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo2Prop;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfo2PropReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfo2PropRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfo2PropRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsLabelRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfo2PropSV;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yx on 2017/4/20.
 */
public class GdsInfo2PropRSVImpl implements IGdsInfo2PropRSV {

    private final static Logger log = LoggerFactory.getLogger(GdsInfo2PropRSVImpl.class);

    @Reference
    private IGdsInfo2PropSV iGdsInfo2PropSV;

    @Override
    public List<GdsInfo2PropRespDTO> queryGdsInfo2PropList(GdsInfo2PropReqDTO gdsInfo2PropReqDTO) throws Exception {
        List<GdsInfo2PropRespDTO> gdsInfo2PropRespDTOList = new ArrayList<GdsInfo2PropRespDTO>();
        if (gdsInfo2PropReqDTO==null){
            throw new Exception("查询商品属性关系列表异常，入参为空");
        }
        try{
            List<GdsInfo2Prop> gdsInfo2PropList = iGdsInfo2PropSV.queryGdsInfo2PropList(gdsInfo2PropReqDTO);
            if(!CollectionUtils.isEmpty(gdsInfo2PropList)){
                for (GdsInfo2Prop gdsInfo2Prop : gdsInfo2PropList){
                    GdsInfo2PropRespDTO gdsInfo2PropRespDTO = new GdsInfo2PropRespDTO();
                    BeanUtils.copyProperties(gdsInfo2Prop,gdsInfo2PropRespDTO);
                    gdsInfo2PropRespDTOList.add(gdsInfo2PropRespDTO);
                }
            }
        }catch (Exception e){
            log.error("查询商品属性关系列表异常：",e);
        }

        return gdsInfo2PropRespDTOList;
    }
}
