package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsSku;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsSkuReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsSkuRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsSkuRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfoSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsSkuSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yx on 2017/4/20.
 */
@Service("gdsSkuRSV")
public class GdsSkuRSVImpl implements IGdsSkuRSV {

    private final static Logger log = LoggerFactory.getLogger(GdsSkuRSVImpl.class);

    @Resource
    private IGdsSkuSV iGdsSkuSV;
    @Resource
    private IGdsInfoSV iGdsInfoSV;

    @Override
    public List<GdsSkuRespDTO> queryGdsSkuList(GdsSkuReqDTO gdsSkuReqDTO) throws Exception {
        List<GdsSkuRespDTO> gdsSkuRespDTOList = new ArrayList<GdsSkuRespDTO>();
        if (gdsSkuReqDTO==null){
            throw new Exception("查询单品列表异常，入参为空");
        }
        try {
            List<GdsSku> gdsSkuList = iGdsSkuSV.queryGdsSkuList(gdsSkuReqDTO);
            if (!CollectionUtil.isEmpty(gdsSkuList)){
                for (GdsSku gdsSku : gdsSkuList){
                    GdsSkuRespDTO gdsSkuRespDTO = new GdsSkuRespDTO();
                    ObjectCopyUtil.copyObjValue(gdsSku,gdsSkuRespDTO,null,false);
                    GdsInfo gdsInfo = iGdsInfoSV.queryGdsInfoById(gdsSku.getGdsId());
                    if (gdsInfo!=null){
                        gdsSkuRespDTO.setGdsPic(gdsInfo.getGdsPic());
                    }
//                    BeanUtils.copyProperties(gdsSku,gdsSkuRespDTO);
                    gdsSkuRespDTOList.add(gdsSkuRespDTO);
                }
            }
        }catch (Exception e){
            log.error("查询单品列表异常：",e);
        }

        return gdsSkuRespDTOList;
    }
    public int insertGdsSku(GdsSkuReqDTO gdsSkuReqDTO) throws Exception {
    	int code=0;
    	 try {
    		 code= iGdsSkuSV.insertGdsSku(gdsSkuReqDTO);
         }catch (Exception e){
             log.error("插入单品信息异常：",e);
         }	
    	 return code;
    }
    public int deleteGdsSkuByGdsId(GdsSkuReqDTO gdsSkuReqDTO) throws Exception{
    	int code=0;
   	 try {
   		 code= iGdsSkuSV.deleteGdsSkuByGdsId(gdsSkuReqDTO);
        }catch (Exception e){
            log.error("删除单品信息异常：",e);
        }	
   	 return code;
    }
    public int updataGdsSkuByGdsId(GdsSkuReqDTO gdsSkuReqDTO) throws Exception{
    	int code=0;
   	 try {
   		 code= iGdsSkuSV.updataGdsSkuByGdsId(gdsSkuReqDTO);
        }catch (Exception e){
            log.error("删除单品信息异常：",e);
        }	
   	 return code;
    }
}
