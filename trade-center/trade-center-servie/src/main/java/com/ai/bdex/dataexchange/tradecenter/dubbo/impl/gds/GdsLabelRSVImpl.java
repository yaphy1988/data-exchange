package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsLabelReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsLabelRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsLabelRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsLabelSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yx on 2017/4/20.
 */
@Service("gdsLabelRSV")
public class GdsLabelRSVImpl implements IGdsLabelRSV {

    private final static Logger log = LoggerFactory.getLogger(GdsLabelRSVImpl.class);

    @Resource
    private IGdsLabelSV iGdsLabelSV;

    @Override
    public List<GdsLabelRespDTO> queryGdsLabelList(GdsLabelReqDTO gdsLabelReqDTO) throws Exception {
        List<GdsLabelRespDTO> gdsLabelRespDTOList = new ArrayList<GdsLabelRespDTO>();
        if (gdsLabelReqDTO==null ){
            throw new Exception("查询商品标签列表为空！");
        }
        try {
            gdsLabelRespDTOList = iGdsLabelSV.queryGdsLabelListDTO(gdsLabelReqDTO);
        }catch (Exception e){
            log.error("查询商品标签列表信息异常：",e);
        }
        return gdsLabelRespDTOList;
    }
    public int insertGdsLabel(GdsLabelReqDTO gdsLabelReqDTO) throws Exception {
		int labelId =0;
		try {
			labelId = iGdsLabelSV.insertGdsLabel(gdsLabelReqDTO);
		} catch (Exception e) {
			log.error("新增商品标签异常:", e);
			throw new Exception(e);
		}
		return labelId;
    }
    public int updateGdsLabel(GdsLabelReqDTO gdsLabelReqDTO) throws Exception {
  		int labelId =0;
  		try {
  			labelId = iGdsLabelSV.updateGdsLabel(gdsLabelReqDTO);
  		} catch (Exception e) {
  			log.error("新增商品标签异常:", e);
  			throw new Exception(e);
  		}
  		return labelId;
      }
    public int deleteGdslabelByGdsId(GdsLabelReqDTO gdsLabelReqDTO) throws Exception {
    	int labelId =0;
		try {
			labelId = iGdsLabelSV.deleteGdslabelByGdsId(gdsLabelReqDTO);
		} catch (Exception e) {
			log.error("删除商品标签异常:", e);
			throw new Exception(e);
		}
		return labelId;
    }
    public int updataGdslabelByGdsId(GdsLabelReqDTO gdsLabelReqDTO) throws Exception {
    	int labelId =0;
		try {
			labelId = iGdsLabelSV.updataGdslabelByGdsId(gdsLabelReqDTO);
		} catch (Exception e) {
			log.error("删除商品标签异常:", e);
			throw new Exception(e);
		}
		return labelId;
    }

}
