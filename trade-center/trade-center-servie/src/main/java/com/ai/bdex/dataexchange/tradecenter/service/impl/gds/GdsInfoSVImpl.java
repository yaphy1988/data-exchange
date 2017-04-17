package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.GdsInfoMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfoSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by yx on 2017/4/17.
 */
@Service("iGdsInfoSV")
public class GdsInfoSVImpl implements IGdsInfoSV{

    @Autowired
    private GdsInfoMapper gdsInfoMapper;

    @Override
    public GdsInfo queryGdsInfoById(Long gdsId) throws Exception {

        GdsInfo gdsInfo = gdsInfoMapper.selectByPrimaryKey(Integer.parseInt(gdsId.longValue()+""));
        return gdsInfo;
    }
}
