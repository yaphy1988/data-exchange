package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.DemoDTO;

/**
 * @author yafei
 * @since 2017/3/31
 */
public interface IDemoRSV {
    String callDemoApi(DemoDTO demoDTO);
}
