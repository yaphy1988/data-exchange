package com.ai.bdex.dataexchange.usercenter.service.impl;

import com.ai.bdex.dataexchange.usercenter.dao.mapper.BaseLoginUrlMapper;
import com.ai.bdex.dataexchange.usercenter.dao.model.BaseLoginUrl;
import com.ai.bdex.dataexchange.usercenter.dao.model.BaseLoginUrlExample;
import com.ai.bdex.dataexchange.usercenter.dao.model.BaseLoginUrlKey;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.IBaseLoginUrlSV;
import com.ai.paas.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ai.bdex.dataexchange.constants.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiongqian on 2017/5/15.
 */
@Service("iBaseLoginUrlSV")
public class IBaseLoginUrlSVImpl implements IBaseLoginUrlSV {

    @Autowired
    private BaseLoginUrlMapper baseLoginUrlMapper;

    @Override
    public List<BaseLoginUrl> getBaseLoginUrls() {
        BaseLoginUrlExample example = new BaseLoginUrlExample();
        example.createCriteria().andStatusEqualTo(Constants.Page.STATUS_VALID);
        return baseLoginUrlMapper.selectByExample(example);
    }

    @Override
    public BaseLoginUrl getBaseLoginUrl(BaseLoginUrlKey key) {

        if(StringUtil.isBlank(key.getSysCode()) || StringUtil.isBlank(key.getUrl())){
            return null;
        }

        return baseLoginUrlMapper.selectByPrimaryKey(key);
    }
}
