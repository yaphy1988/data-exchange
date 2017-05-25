package com.ai.bdex.dataexchange.usercenter.service.interfaces;

import com.ai.bdex.dataexchange.usercenter.dao.model.BaseLoginUrl;
import com.ai.bdex.dataexchange.usercenter.dao.model.BaseLoginUrlKey;

import java.util.List;

/**
 * Created by xiongqian on 2017/5/15.
 */
public interface IBaseLoginUrlSV {
    /**
     * 获取免登陆配置URL
     * @return
     */
    public List<BaseLoginUrl> getBaseLoginUrls();

    public  BaseLoginUrl getBaseLoginUrl(BaseLoginUrlKey key);
}
