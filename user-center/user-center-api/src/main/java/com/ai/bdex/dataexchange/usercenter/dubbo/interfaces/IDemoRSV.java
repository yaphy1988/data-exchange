package com.ai.bdex.dataexchange.usercenter.dubbo.interfaces;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.DemoDTO;

/**
 * @author yafei
 * @since 2017/3/31
 */
public interface IDemoRSV {
    String callDemoApi(DemoDTO demoDTO);
}
