package com.ai.bdex.dataexchange.usercenter.dubbo.impl;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dao.model.BaseAdminArea;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.BaseAdminAreaReqDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.BaseAdminAreaRespDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IBaseAdminAreaRSV;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.IBaseAdminAreaSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.paas.utils.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yx on 2017/5/9.
 */
@Service("iBaseAdminAreaRSV")
public class BaseAdminAreaRSVImpl implements IBaseAdminAreaRSV{

    private final static Logger log = LoggerFactory.getLogger(BaseAdminAreaRSVImpl.class);

    @Autowired
    private IBaseAdminAreaSV iBaseAdminAreaSV;

    @Override
    public List<BaseAdminAreaRespDTO> queryBaseAdminAreaList(BaseAdminAreaReqDTO baseAdminAreaReqDTO) throws Exception {
        if (baseAdminAreaReqDTO==null){
            throw new BusinessException("查询区域列表入参为空");
        }
        List<BaseAdminAreaRespDTO> list = new ArrayList<BaseAdminAreaRespDTO>();
        try {
            List<BaseAdminArea> baseAdminAreaList = iBaseAdminAreaSV.queryBaseAdminAreaList(baseAdminAreaReqDTO);
            if (!CollectionUtil.isEmpty(baseAdminAreaList)){
                for (BaseAdminArea baseAdminArea : baseAdminAreaList){
                    BaseAdminAreaRespDTO baseAdminAreaRespDTO = new BaseAdminAreaRespDTO();
                    ObjectCopyUtil.copyObjValue(baseAdminArea,baseAdminAreaRespDTO,null,false);
                    list.add(baseAdminAreaRespDTO);
                }
            }
        }catch (Exception e){
            log.error("查询区域列表异常：",e);
        }

        return list;
    }
}
