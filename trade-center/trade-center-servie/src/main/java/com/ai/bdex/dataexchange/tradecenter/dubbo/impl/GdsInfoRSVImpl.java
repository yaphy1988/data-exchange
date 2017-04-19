package com.ai.bdex.dataexchange.tradecenter.dubbo.impl;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsCat;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsLabel;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsSku;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.*;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.Gds.IGdsInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.Gds.IGdsInfoRSVImpl;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsCatSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfoSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsLabelSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsSkuSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yx on 2017/4/18.
 */
public class GdsInfoRSVImpl implements IGdsInfoRSV {

    private static final Logger log = LoggerFactory.getLogger(GdsInfoRSVImpl.class);

    @Resource
    private IGdsInfoSV iGdsInfoSV;

    @Resource
    private IGdsCatSV iGdsCatSV;

    @Resource
    private IGdsLabelSV iGdsLabelSV;

    @Resource
    private IGdsSkuSV iGdsSkuSV;

    @Override
    public GdsInfoRespDTO queryGdsInfoDetails(GdsInfoReqDTO gdsInfoReqDTO) throws Exception {
        GdsInfoRespDTO gdsInfoRespDTO = new GdsInfoRespDTO();
        if (gdsInfoReqDTO ==null || gdsInfoReqDTO.getGdsId()==null || gdsInfoReqDTO.getGdsId().intValue()<=0){
            throw new Exception("查询商品详细信息异常，入参为空");
        }

        GdsInfo gdsInfo = iGdsInfoSV.queryGdsInfoById(gdsInfoReqDTO.getGdsId());
        if(gdsInfo!=null){
            BeanUtils.copyProperties(gdsInfo, gdsInfoRespDTO);
            //set商品第一类目名称
            if (gdsInfo.getCatFirst()!=null && gdsInfo.getCatFirst().intValue()>0){
                GdsCat gdsCat = iGdsCatSV.queryGdsCatById(gdsInfo.getCatFirst());
                if(gdsCat!=null){
                    gdsInfoRespDTO.setCatFirstName(gdsCat.getCatName());
                }
            }
            //获得商品标签列表
            List<GdsLabelRespDTO> gdsLabelRespDTOs = new ArrayList<GdsLabelRespDTO>();
            GdsLabelReqDTO gdsLabelReqDTO = new GdsLabelReqDTO();
            gdsLabelReqDTO.setGdsId(gdsInfo.getGdsId());
            gdsLabelReqDTO.setStatus("1");
            List<GdsLabel> gdsLabels = iGdsLabelSV.queryGdsLabelList(gdsLabelReqDTO);
            if(!CollectionUtils.isEmpty(gdsLabels)){
                for (GdsLabel gdsLabel : gdsLabels){
                    GdsLabelRespDTO gdsLabelRespDTO = new GdsLabelRespDTO();
                    BeanUtils.copyProperties(gdsLabel, gdsLabelRespDTO);
                    gdsLabelRespDTOs.add(gdsLabelRespDTO);
                }
                gdsInfoRespDTO.setGdsLabelRespDTOs(gdsLabelRespDTOs);
            }
            //获得商品的单品列表
            List<GdsSkuRespDTO> gdsSkuRespDTOs = new ArrayList<GdsSkuRespDTO>();
            GdsSkuReqDTO gdsSkuRepVO = new GdsSkuReqDTO();
            gdsSkuRepVO.setStatus("1");
            gdsSkuRepVO.setGdsId(gdsInfo.getGdsId());
            List<GdsSku> gdsSkus =iGdsSkuSV.queryGdsSkuList(gdsSkuRepVO);
            if(!CollectionUtils.isEmpty(gdsSkus)){
                for (GdsSku gdsSku : gdsSkus){
                    GdsSkuRespDTO gdsSkuRespDTO = new GdsSkuRespDTO();
                    BeanUtils.copyProperties(gdsSku, gdsSkuRespDTO);
                    gdsSkuRespDTOs.add(gdsSkuRespDTO);
                }
                gdsInfoRespDTO.setGdsSkuRespDTOList(gdsSkuRespDTOs);
            }

        }else{
            return null;
        }

        return null;
    }
    @Override
    public List<GdsCatRespDTO> queryGdsCatListDTO(GdsCatReqDTO gdsCatReqDTO) throws Exception {
        List<GdsCatRespDTO> respDTOList = new ArrayList<GdsCatRespDTO>();
        try{
        	 if (gdsCatReqDTO ==null || gdsCatReqDTO.getCatPid()==null){
                 throw new Exception("查询商品分类信息异常，入参为空");
             }
             respDTOList= iGdsCatSV.queryGdsCatListDTO(gdsCatReqDTO);
        }catch(Exception e){
        	log.error("获取商品分类信息异常:", e);
            throw new Exception(e);
        }
        return respDTOList;
    }
}
