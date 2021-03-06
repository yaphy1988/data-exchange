package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo2Prop;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfo2PropReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfo2PropRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfo2PropRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfo2PropSV;
import com.ai.paas.utils.CollectionUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * Created by yx on 2017/4/20.
 */
@Service("gdsInfo2PropRSV")
public class GdsInfo2PropRSVImpl implements IGdsInfo2PropRSV {

    private final static Logger log = LoggerFactory.getLogger(GdsInfo2PropRSVImpl.class);

    @Resource
    private IGdsInfo2PropSV iGdsInfo2PropSV;

    @Override
    public List<GdsInfo2PropRespDTO> queryGdsInfo2PropList(GdsInfo2PropReqDTO gdsInfo2PropReqDTO) throws Exception {
        List<GdsInfo2PropRespDTO> gdsInfo2PropRespDTOList = new ArrayList<GdsInfo2PropRespDTO>();
        if (gdsInfo2PropReqDTO==null){
            throw new Exception("查询商品属性关系列表异常，入参为空");
        }
        try{
            List<GdsInfo2Prop> gdsInfo2PropList = iGdsInfo2PropSV.queryGdsInfo2PropList(gdsInfo2PropReqDTO);
            if(!CollectionUtil.isEmpty(gdsInfo2PropList)){
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
    public int insertGdsInfo2Prop(GdsInfo2PropReqDTO gdsInfo2PropReqDTO) throws Exception{
    	int code=0;
        try{
        	code = iGdsInfo2PropSV.insertGdsInfo2Prop(gdsInfo2PropReqDTO);
        }catch (Exception e){
            log.error("查询商品属性关系列表异常：",e);
        }

        return code;
    }
    public int updateGdsInfo2Prop(GdsInfo2PropReqDTO gdsInfo2PropReqDTO) throws Exception {
    	int code=0;
        try{
        	code = iGdsInfo2PropSV.updateGdsInfo2Prop(gdsInfo2PropReqDTO);
        }catch (Exception e){
            log.error("更新商品属性关系列表异常：",e);
        }

        return code;
    }
}
