package com.ai.bdex.dataexchange.apigateway;

import com.ai.bdex.dataexchange.apigateway.dubbo.dto.DataConsumeDTO;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IApiGatewayDataAccountSV;
import com.ai.bdex.dataexchange.exception.BusinessException;
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
        consumeDTO.setUserId("fangyf007");
        consumeDTO.setRealServiceId("getlocationinterface");
        consumeDTO.setInvokeSeq("201705120000001");
        consumeDTO.setConsumeNum(1);
        consumeDTO.setConsumeMoney(100);
        String result = apiGatewayDataAccountSV.dataCharge(consumeDTO);
        System.out.println("result="+result);
    }
}
