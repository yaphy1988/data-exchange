package com.ai.bdex.dataexchange.apigateway;

import com.ai.bdex.dataexchange.apigateway.dubbo.dto.DataConsumeDTO;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.DataConsumeRespDTO;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IApiGatewayDataAccountSV;
import com.ai.bdex.dataexchange.exception.BusinessException;
import org.apache.ibatis.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by fangyunfeng on 2017/5/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DataAccountRSVTest {
    @Autowired
    private IApiGatewayDataAccountSV apiGatewayDataAccountSV;

    @Test
    public void dataChargeTest() throws BusinessException {
        DataConsumeDTO consumeDTO = new DataConsumeDTO();
        consumeDTO.setUserId("lush11");
        consumeDTO.setRealServiceId("1012");
        consumeDTO.setInvokeSeq("201706060000002");
        consumeDTO.setConsumeNum(1);
        consumeDTO.setConsumeMoney(1);
        DataConsumeRespDTO result = apiGatewayDataAccountSV.dealDataCharge(consumeDTO);
        System.out.println("result="+result.getResult());
    }
}
