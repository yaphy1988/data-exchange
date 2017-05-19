package com.ai.bdex.dataexchange.aipcenter;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.RechargeDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.RechargeReqDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipCenterDataAccountRSV;
import com.ai.bdex.dataexchange.constants.Constants;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.paas.util.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by fangyunfeng on 2017/5/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DataAccountRSVTest {

    @Autowired
    private IAipCenterDataAccountRSV aipCenterDataAccountRSV;

    @Test
    public void testRecharge() throws BusinessException {


        RechargeReqDTO rechargeDTO = new RechargeReqDTO();
        rechargeDTO.setRechargeUserId("fangyf007");
        rechargeDTO.setSubOrder("20170512000000001-1");
        rechargeDTO.setRechargeType(Constants.Bill.RECHARGE_TYPE_NUM);
        rechargeDTO.setOrderId("20170512000000001");
        rechargeDTO.setServiceId("getlocationinterface");
        rechargeDTO.setTotalNum(10000);
        rechargeDTO.setTotalMoney(1000000);
        rechargeDTO.setPeriodType(Constants.Bill.DATA_ACCT_PERIOD_VALID);
        rechargeDTO.setStartDate(new Date());
        rechargeDTO.setEndDate(new Date(System.currentTimeMillis()+15552000000L));

        aipCenterDataAccountRSV.dealRecharge(rechargeDTO);
    }

    @Test
    public void testDealDisableDataAccount() throws BusinessException {
        aipCenterDataAccountRSV.dealDisableDataAccount(10022);
    }
}
