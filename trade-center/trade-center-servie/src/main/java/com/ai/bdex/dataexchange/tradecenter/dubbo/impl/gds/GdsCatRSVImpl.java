package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsCat;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsCatReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsCatRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsCatRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsCatSV;
import com.alibaba.dubbo.common.utils.CollectionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * Created by yx on 2017/4/20.
 */
@Service("gdsCatRSV")
public class GdsCatRSVImpl implements IGdsCatRSV {

    private final static Logger log = LoggerFactory.getLogger(GdsCatRSVImpl.class);

    @Resource
    private IGdsCatSV iGdsCatSV;
    @Override
    public GdsCatRespDTO queryGdsCatByCatId(Integer catId) throws Exception {
        GdsCatRespDTO gdsCatRespDTO = new GdsCatRespDTO();
        if (catId==null || catId.intValue()<=0){
            throw new Exception("查询商品分类信息入参为空");
        }
        try{
            GdsCat gdsCat = iGdsCatSV.queryGdsCatById(catId);
            if (gdsCat!=null){
                BeanUtils.copyProperties(gdsCat,gdsCatRespDTO);
            }else{
                return null;
            }
        }catch (Exception e){
            log.error("查询商品分类信息异常",e);
        }
        return gdsCatRespDTO;
    }
    @Override
    public List<GdsCatRespDTO> queryGdsCatList(GdsCatReqDTO gdsCatReqDTO) throws Exception {
        List<GdsCatRespDTO> respDTOList = new ArrayList<GdsCatRespDTO>();
        try{
        	 if (gdsCatReqDTO ==null || gdsCatReqDTO.getCatPid()==null){
                 throw new Exception("查询商品分类信息异常，入参为空");
             }
             List<GdsCat> catList= iGdsCatSV.queryGdsCatList(gdsCatReqDTO);
             if(CollectionUtils.isNotEmpty(catList)){
            	 GdsCatRespDTO respDTO = new GdsCatRespDTO();
            	 for(GdsCat catVO:catList){
            		 BeanUtils.copyProperties(catVO,respDTO);
            		 respDTOList.add(respDTO);
            	 }
             }
        }catch(Exception e){
        	log.error("获取商品分类信息异常:", e);
            throw new Exception(e);
        }
        return respDTOList;
    }
}
