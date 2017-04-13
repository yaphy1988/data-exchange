package com.ai.bdex.dataexchange.usercenter.service.interfaces;


import com.ai.bdex.dataexchange.usercenter.dubbo.dto.DemoDTO;

/**
 * Created by fangyunfeng on 2017/4/12.
 */
public interface IDemoSV {
    String callDemo(DemoDTO demoDTO);
}
