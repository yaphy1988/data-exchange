package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.order;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInvoiceTaxReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrdInvoiceTaxRSV;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author yafei
 * @since 2017/5/10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OrdInvoiceTaxRSVImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    IOrdInvoiceTaxRSV iOrdInvoiceTaxRSV;

    @Test
    public void insertOrdInvoice() throws Exception {
        OrdInvoiceTaxReqDTO ordInvoiceTaxReqDTO = new OrdInvoiceTaxReqDTO();
        ordInvoiceTaxReqDTO.setAcctInfo("123");
        ordInvoiceTaxReqDTO.setTaxId(new Long(10010));

//        Long orderTaxId = iOrdInvoiceTaxRSV.insertOrdInvoice(ordInvoiceTaxReqDTO);
//        assertNotNull(orderTaxId);

        thrown.expect(Exception.class);
        iOrdInvoiceTaxRSV.insertOrdInvoice(ordInvoiceTaxReqDTO);
        System.out.print("------OrdInvoiceTaxRSVImplTest------------");
    }

}