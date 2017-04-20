package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsCat;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsCatRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsCatRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsCatSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
}
