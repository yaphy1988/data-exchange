package com.ai.bdex.dataexchange.usercenter.dubbo.impl;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.DemoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.ReqDemoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IDemoRSV;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.IDemoSV;
import com.ai.bdex.dataexchange.util.ExceptionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yafei
 * @since 2017/3/31
 */
@Service("demoRSV")
public class DemoRSVImpl implements IDemoRSV {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private IDemoSV demoSV;

    @Override
    public String callDemoApi(DemoDTO demoDTO) {
        return demoSV.callDemo(demoDTO);
    }

    @Override
    public PageResponseDTO<DemoDTO> queryDemoPage(ReqDemoDTO baseInfo) throws BusinessException {
        PageResponseDTO<DemoDTO> respDTO;
        try {
            respDTO = demoSV.queryDemoPage(baseInfo);
        } catch (Exception e) {
            logger.error("queryDemoInfo异常", e);
            throw ExceptionFactory.buildABusinessException(e);
        }
        return respDTO;
    }
}
