package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsCat;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsCatSV;
import org.springframework.stereotype.Service;

/**
 * Created by yx on 2017/4/17.
 */
@Service("iGdsCatSV")
public class GdsCatSVImpl implements IGdsCatSV{

    @Override
    public GdsCat queryGdsCatById(Long catId) throws Exception {
        return null;
    }
}
