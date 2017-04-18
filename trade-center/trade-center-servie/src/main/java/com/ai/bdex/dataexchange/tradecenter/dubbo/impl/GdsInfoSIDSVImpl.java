package com.ai.bdex.dataexchange.tradecenter.dubbo.impl;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsCat;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsLabel;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfoReqVO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfoRespVO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsLabelReqVO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsLabelRespVO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.Gds.IGdsInfoSIDSVImpl;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsCatSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfoSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsLabelSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by yx on 2017/4/18.
 */
public class GdsInfoSIDSVImpl implements IGdsInfoSIDSVImpl {

    private static final Logger log = LoggerFactory.getLogger(GdsInfoSIDSVImpl.class);

    @Resource
    private IGdsInfoSV iGdsInfoSV;

    @Resource
    private IGdsCatSV iGdsCatSV;

    @Resource
    private IGdsLabelSV iGdsLabelSV;

    @Override
    public GdsInfoRespVO queryGdsInfoDetails(GdsInfoReqVO gdsInfoReqVO) throws Exception {
        GdsInfoRespVO gdsInfoRespVO = new GdsInfoRespVO();
        if (gdsInfoReqVO==null || gdsInfoReqVO.getGdsId()==null || gdsInfoReqVO.getGdsId().intValue()<=0){
            throw new Exception("查询商品详细信息异常，入参为空");
        }

        GdsInfo gdsInfo = iGdsInfoSV.queryGdsInfoById(Long.valueOf(gdsInfoReqVO.getGdsId()));
        if(gdsInfo!=null){
            BeanUtils.copyProperties(gdsInfo,gdsInfoRespVO);
            //set商品第一类目名称
            if (gdsInfo.getCatFirst()!=null && gdsInfo.getCatFirst().intValue()>0){
                GdsCat gdsCat = iGdsCatSV.queryGdsCatById(Long.valueOf(gdsInfo.getCatFirst()));
                if(gdsCat!=null){
                    gdsInfoRespVO.setCatFirstName(gdsCat.getCatName());
                }
            }
            //获得商品标签列表
            List<GdsLabelRespVO> gdsLabelRespVOs = new ArrayList<GdsLabelRespVO>();
            GdsLabelReqVO gdsLabelReqVO = new GdsLabelReqVO();
            gdsLabelReqVO.setGdsId(Integer.valueOf(gdsInfo.getGdsId()));
            gdsLabelReqVO.setStatus("1");
            List<GdsLabel> gdsLabels = iGdsLabelSV.queryGdsLabelList(gdsLabelReqVO);
            if(!CollectionUtils.isEmpty(gdsLabels)){
                for (GdsLabel gdsLabel : gdsLabels){
                    GdsLabelRespVO gdsLabelRespVO = new GdsLabelRespVO();
                    BeanUtils.copyProperties(gdsLabel,gdsLabelRespVO);
                    gdsLabelRespVOs.add(gdsLabelRespVO);
                }
                gdsInfoRespVO.setGdsLabelRespVOs(gdsLabelRespVOs);
            }
        }else{
            return null;
        }

        return null;
    }
}
