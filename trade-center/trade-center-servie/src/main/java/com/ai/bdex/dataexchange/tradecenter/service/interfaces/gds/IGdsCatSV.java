package com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsCat;

/**
 * Created by yx on 2017/4/17.
 */
public interface IGdsCatSV {
    public GdsCat queryGdsCatById(Long catId) throws Exception;
}
