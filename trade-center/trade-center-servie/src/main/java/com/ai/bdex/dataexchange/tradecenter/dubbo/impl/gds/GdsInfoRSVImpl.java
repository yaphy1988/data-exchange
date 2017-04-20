package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsCat;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsLabel;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsProp;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsSku;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.*;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsCatSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfoSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsLabelQuikSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsLabelSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsPropSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsSkuSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yx on 2017/4/18.
 */
@Service("gdsInfoRSV")
public class GdsInfoRSVImpl implements IGdsInfoRSV {

    private static final Logger log = LoggerFactory.getLogger(GdsInfoRSVImpl.class);

    private final static Integer AIP_CAT_ID = 1;

    @Resource
    private IGdsInfoSV iGdsInfoSV;

    @Resource
    private IGdsCatSV iGdsCatSV;

    @Resource
    private IGdsLabelSV iGdsLabelSV;

    @Resource
    private IGdsSkuSV iGdsSkuSV;
    
    @Resource
    private IGdsLabelQuikSV iGdsLabelQuikSV;
    
    private IGdsPropSV iGdsPropSV;
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

            if (AIP_CAT_ID.equals(gdsInfoRespDTO.getCatFirst())){
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
            }
        }else{
            return null;
        }

        return gdsInfoRespDTO;
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
    public List<GdsLabelRespDTO> queryGdsLabelListDTO(GdsLabelReqDTO gdsLabelReqDTO) throws Exception {
        List<GdsLabelRespDTO> respDTOList = new ArrayList<GdsLabelRespDTO>();
    	 try{
             respDTOList= iGdsLabelSV.queryGdsLabelListDTO(gdsLabelReqDTO);
        }catch(Exception e){
        	log.error("获取商品标签信息异常:", e);
            throw new Exception(e);
        }
        return respDTOList;
    }
    public List<GdsLabelQuikRespDTO> queryGdsLabelQuikListDTO(GdsLabelQuikReqDTO gdsLabelQuikReqDTO) throws Exception {
        List<GdsLabelQuikRespDTO> respDTOList = new ArrayList<GdsLabelQuikRespDTO>();
    	 try{
             respDTOList= iGdsLabelQuikSV.queryGdsLabelQuikListDTO(gdsLabelQuikReqDTO);
        }catch(Exception e){
        	log.error("获取商品标签快速选择数据信息异常:", e);
            throw new Exception(e);
        }
        return respDTOList;
    }
    	
	public GdsResultVO addGds(GdsInfoReqDTO reqDTO) throws Exception {
		GdsResultVO resultVO = new GdsResultVO();
		try{
			 if (reqDTO ==null){
	             throw new Exception("新增商品信息异常，入参为空");
	         }
			 resultVO=iGdsInfoSV.addGds(reqDTO);
		}catch(Exception e){
        	log.error("新增商品信息异常:", e);
            throw new Exception(e);
        }
		return resultVO;
	}

    @Override
    public List<GdsInfoRespDTO> queryGdsInfoList(GdsInfoReqDTO gdsInfoReqDTO) throws Exception {
        List<GdsInfoRespDTO> gdsInfoRespDTOList = new ArrayList<GdsInfoRespDTO>();
        try {
            List<GdsInfo> gdsInfoList = iGdsInfoSV.queryGdsInfoList(gdsInfoReqDTO);
            if (!CollectionUtils.isEmpty(gdsInfoList)){
                for (GdsInfo gdsInfo : gdsInfoList){
                    GdsInfoRespDTO gdsInfoRespDTO = new GdsInfoRespDTO();
                    BeanUtils.copyProperties(gdsInfo,gdsInfoRespDTO);
                    gdsInfoRespDTOList.add(gdsInfoRespDTO);
                }
            }
        }catch (Exception e){
            log.error("查询商品列表信息异常：",e);
        }
        return gdsInfoRespDTOList;
    }

	public GdsResultVO editGds(GdsInfoReqDTO reqDTO) throws Exception {
		GdsResultVO resultVO = new GdsResultVO();
		try{
			 if (reqDTO ==null&&reqDTO.getGdsId()==0){
	             throw new Exception("编辑商品信息异常，入参为空");
	         }
			 resultVO=iGdsInfoSV.editGds(reqDTO);
		}catch(Exception e){
        	log.error("编辑商品信息异常:", e);
            throw new Exception(e);
        }
		return resultVO;
	}
	 public GdsInfoRespDTO queryGdsInfo(GdsInfoReqDTO gdsInfoReqDTO) throws Exception {
		 GdsInfoRespDTO respDTO = new GdsInfoRespDTO();
		 try{
			 if (gdsInfoReqDTO ==null || gdsInfoReqDTO.getGdsId()==null || gdsInfoReqDTO.getGdsId().intValue()<=0){
		            throw new Exception("查询商品信息异常，入参为空");
		      }
			 respDTO=iGdsInfoSV.queryGdsInfo(gdsInfoReqDTO);
		}catch(Exception e){
        	log.error("查询商品信息异常:", e);
            throw new Exception(e);
        }
		return respDTO;
	 }
	 public List<GdsPropRespDTO> queryGdsPropList(GdsPropReqDTO gdsPropReqDTO) throws Exception {
		 List<GdsPropRespDTO> respDTOList = new ArrayList<GdsPropRespDTO>();
		try{
			List<GdsProp> propList=iGdsPropSV.queryGdsPropList(gdsPropReqDTO);
			 if (!CollectionUtils.isEmpty(propList)){
	                for (GdsProp propInfo : propList){
	                	GdsPropRespDTO gdsPropRespDTO = new GdsPropRespDTO();
	                    BeanUtils.copyProperties(propInfo,gdsPropRespDTO);
	                    respDTOList.add(gdsPropRespDTO);
	                }
	            }
		}catch(Exception e){
        	log.error("查询分类属性异常:", e);
            throw new Exception(e);
        }
		return respDTOList;
	 }
}
