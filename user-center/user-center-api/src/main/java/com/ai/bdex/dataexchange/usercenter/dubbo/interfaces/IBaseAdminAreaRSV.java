package com.ai.bdex.dataexchange.usercenter.dubbo.interfaces;

import com.ai.bdex.dataexchange.usercenter.dubbo.dto.BaseAdminAreaReqDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.BaseAdminAreaRespDTO;

import java.util.List;

/**
 * Created by yx on 2017/5/9.
 */
public interface IBaseAdminAreaRSV {

    public List<BaseAdminAreaRespDTO> queryBaseAdminAreaList(BaseAdminAreaReqDTO baseAdminAreaReqDTO) throws Exception;
}
