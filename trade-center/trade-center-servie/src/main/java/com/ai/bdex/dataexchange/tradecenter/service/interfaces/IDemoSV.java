package com.ai.bdex.dataexchange.tradecenter.service.interfaces;


import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.DemoDTO;

/**
 * Created by fangyunfeng on 2017/4/12.
 */
public interface IDemoSV {
    String callDemo(DemoDTO demoDTO);
}
