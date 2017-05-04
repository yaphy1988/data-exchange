package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.gds;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsCat;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsCatRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsCatSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.paas.utils.CollectionUtil;

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
                ObjectCopyUtil.copyObjValue(gdsCat,gdsCatRespDTO,null,false);
//                BeanUtils.copyProperties(gdsCat,gdsCatRespDTO);
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
        	 if (gdsCatReqDTO ==null){
                 throw new Exception("查询商品分类信息异常，入参为空");
             }
             List<GdsCat> catList= iGdsCatSV.queryGdsCatList(gdsCatReqDTO);

             if(!CollectionUtil.isEmpty(catList)){
            	 for(GdsCat catVO:catList){
                     GdsCatRespDTO respDTO = new GdsCatRespDTO();
                     ObjectCopyUtil.copyObjValue(catVO,respDTO,null,false);
//            		 BeanUtils.copyProperties(catVO,respDTO);
            		 respDTOList.add(respDTO);
            	 }
             }
        }catch(Exception e){
        	log.error("获取商品分类信息异常:", e);
            throw new Exception(e);
        }
        return respDTOList;
    }

    @Override
    public List<GdsCatRespDTO> queryLadderCatListByCatId(Integer catId) throws Exception {
        if (catId == null){
            throw new Exception("根据当前ID获取从最低级到最高级分类的入参为空");
        }
        List<GdsCatRespDTO> list = new ArrayList<GdsCatRespDTO>();
        try {
            queryLadderCatList(list,catId);
        }catch (Exception e){
            log.error("根据当前ID获取从最低级到最高级分类异常：",e);
        }
        return list;
    }

    private void queryLadderCatList(List<GdsCatRespDTO> list , Integer catId){
        GdsCatRespDTO gdsCatRespDTO = null;
        try {
            gdsCatRespDTO = queryGdsCatByCatId(catId);
            if (gdsCatRespDTO!=null){
                list.add(gdsCatRespDTO);
                if (gdsCatRespDTO.getCatPid().intValue()>0){
                    queryLadderCatList(list,gdsCatRespDTO.getCatPid());
                }
            }
        }catch (Exception e){
            log.error("查询分类递进列表异常：",e);
        }
    }
    @Override
    public PageResponseDTO<GdsCatRespDTO> queryCatPageInfo(GdsCatReqDTO gdsCatReqDTO)
            throws BusinessException {
        return iGdsCatSV.queryCatPageInfo(gdsCatReqDTO);
    }
}
