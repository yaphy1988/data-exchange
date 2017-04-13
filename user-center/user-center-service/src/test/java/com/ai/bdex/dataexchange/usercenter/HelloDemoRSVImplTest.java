package com.ai.bdex.dataexchange.usercenter;

import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IDemoRSV;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @author yafei
 * @since 2017/3/31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ImportResource({"classpath:/dubbo/dubboContext.xml"})
public class HelloDemoRSVImplTest {

    @Autowired
    private IDemoRSV demoRSV;
	
    @Test
    public void helloWorld() throws Exception {
        demoRSV.callDemoApi(null);
    }
}