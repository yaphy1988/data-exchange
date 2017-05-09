package com.ai.bdex.dataexchange.usercenter.service.interfaces;

import com.ai.bdex.dataexchange.usercenter.dao.model.BaseAdminArea;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.BaseAdminAreaReqDTO;

import java.util.List;

/**
 * Created by yx on 2017/5/9.
 */
public interface IBaseAdminAreaSV {

    public List<BaseAdminArea> queryBaseAdminAreaList(BaseAdminAreaReqDTO baseAdminAreaReqDTO) throws Exception;
}
