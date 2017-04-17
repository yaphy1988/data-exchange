package com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo;

/**
 * Created by yx on 2017/4/17.
 */
public interface IGdsInfoSV {

    public GdsInfo queryGdsInfoById(Long gdsId) throws Exception;
}
