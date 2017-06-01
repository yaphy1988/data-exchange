package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.gds;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsCat;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsLabel;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsProp;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsSku;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfo2CatReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsLabelQuikReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsLabelQuikRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsLabelReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsLabelRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsPropReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsPropRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsResultVO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsSkuReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsSkuRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.solr.SolrCoreEnum;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsCatSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfo2CatSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfoSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsLabelQuikSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsLabelSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsPropSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsSkuSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.solr.IDeltaIndexServiceSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.paas.utils.CollectionUtil;

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
    @Resource
    private IGdsPropSV iGdsPropSV;
    
    @Resource
    private IGdsInfo2CatSV iGdsInfo2CatSV;

    @Resource
    private IDeltaIndexServiceSV iDeltaIndexServiceSV;
    
    @Override
    public GdsInfoRespDTO queryGdsInfoDetails(GdsInfoReqDTO gdsInfoReqDTO) throws Exception {
        GdsInfoRespDTO gdsInfoRespDTO = new GdsInfoRespDTO();
        if (gdsInfoReqDTO ==null || gdsInfoReqDTO.getGdsId()==null || gdsInfoReqDTO.getGdsId().intValue()<=0){
            throw new Exception("查询商品详细信息异常，入参为空");
        }

        GdsInfo gdsInfo = iGdsInfoSV.queryGdsInfoById(gdsInfoReqDTO.getGdsId());
        if(gdsInfo!=null){
            ObjectCopyUtil.copyObjValue(gdsInfo,gdsInfoRespDTO,null,false);
//            BeanUtils.copyProperties(gdsInfo, gdsInfoRespDTO);
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
            if(!CollectionUtil.isEmpty(gdsLabels)){
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
                if(!CollectionUtil.isEmpty(gdsSkus)){
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
            if (!CollectionUtil.isEmpty(gdsInfoList)){
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
			 if (!CollectionUtil.isEmpty(propList)){
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

	public int insertGdsInfo(GdsInfoReqDTO gdsInfoReqDTO) throws Exception {
		int gdsId =0;
		try {
			gdsId = iGdsInfoSV.insertGdsInfo(gdsInfoReqDTO);
		} catch (Exception e) {
			log.error("新增商品基本信息异常:", e);
			throw new Exception(e);
		}
		return gdsId;
	}
	public int updateGdsInfo(GdsInfoReqDTO gdsInfoReqDTO) throws Exception {
		int gdsId =0;
		try {
			gdsId = iGdsInfoSV.updateGdsInfo(gdsInfoReqDTO);
		} catch (Exception e) {
			log.error("更新商品基本信息异常:", e);
			throw new Exception(e);
		}
		return gdsId;
	}
	public int insertGdsLabelQuik(GdsLabelQuikReqDTO gdsLabelQuikReqDTO) throws Exception {
		int labelId =0;
		try {
			labelId = iGdsLabelQuikSV.insertGdsLabelQuik(gdsLabelQuikReqDTO);
		} catch (Exception e) {
			log.error("新增商品基本信息异常:", e);
			throw new Exception(e);
		}
		return labelId;
	}
    public GdsLabelQuikRespDTO queryGdsLabelQuikByName(GdsLabelQuikReqDTO gdsLabelQuikReqDTO) throws Exception{
    	GdsLabelQuikRespDTO resp = new GdsLabelQuikRespDTO();
    	try {
    		resp = iGdsLabelQuikSV.queryGdsLabelQuikByName(gdsLabelQuikReqDTO);
		} catch (Exception e) {
			log.error("新增商品基本信息异常:", e);
			throw new Exception(e);
		}
    	return resp;
    }
    public int insertGdsInfo2Cat(GdsInfo2CatReqDTO gdsInfo2CatReqDTO) throws Exception {
		int gcId =0;
		try {
			gcId = iGdsInfo2CatSV.insertGdsInfo2Cat(gdsInfo2CatReqDTO);
		} catch (Exception e) {
			log.error("新增商品基本信息异常:", e);
			throw new Exception(e);
		}
		return gcId;
	}
    public int updateGdsInfo2Cat(GdsInfo2CatReqDTO gdsInfo2CatReqDTO) throws Exception {
		int gcId =0;
		try {
			gcId = iGdsInfo2CatSV.updateGdsInfo2Cat(gdsInfo2CatReqDTO);
		} catch (Exception e) {
			log.error("新增商品基本信息异常:", e);
			throw new Exception(e);
		}
		return gcId;
	}

    @Override
    public PageResponseDTO<GdsInfoRespDTO> queryGdsInfoPage(GdsInfoReqDTO gdsInfoReqDTO) {
        PageResponseDTO<GdsInfoRespDTO> pageResponseDTO = new PageResponseDTO<GdsInfoRespDTO>();
        try {
            pageResponseDTO = iGdsInfoSV.queryGdsInfoPage(gdsInfoReqDTO);
        }catch (Exception e){
            log.error("查询商品列表分页信息异常:",e);
        }
        return pageResponseDTO;
    }

    @Override
    public int updateGdsInfoManager(GdsInfoReqDTO gdsInfoReqDTO) throws Exception {
        int gdsId =0;
        try {
            gdsId = iGdsInfoSV.updateGdsInfo(gdsInfoReqDTO);

        } catch (Exception e) {
            log.error("跟新商品基本信息异常:", e);
            throw new Exception(e);
        }
        try{
            if (gdsId!=0){
                if ("1".equals(gdsInfoReqDTO.getStatus())){
                    iDeltaIndexServiceSV.deltaImport(SolrCoreEnum.GDS.getCode(),gdsId);
                }else if ("2".equals(gdsInfoReqDTO.getStatus())){
                    List<Integer> gdsIds = new ArrayList<Integer>();
                    gdsIds.add(gdsId);
                    iDeltaIndexServiceSV.deleteDeltaBatch(SolrCoreEnum.GDS.getCode(),gdsIds);
                }
            }
        }catch (Exception e){
            log.error("调用solr服务更新异常：",e);
        }
        return gdsId;
    }
    @Override
    public long count(GdsInfoReqDTO gdsInfoReqDTO) throws BusinessException {
        try{
            if (gdsInfoReqDTO ==null){
                   throw new Exception("统计商品数量异常，入参为空");
             }
            return iGdsInfoSV.count(gdsInfoReqDTO);
       }catch(Exception e){
           log.error("统计商品数量异常:", e);
           throw new BusinessException(e);
          
       }
    }
}
