package com.ai.bdex.dataexchange.usercenter;

import com.ai.bdex.dataexchange.sequence.SeqUtil;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IDemoRSV;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.IDemoSV;
import com.ai.paas.util.CacheUtil;
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

    @Test
    public void seqUtilTest(){
        System.out.println("int value="+SeqUtil.getInt("SEQ_DEMO"));
        System.out.println("long value="+SeqUtil.getLong("SEQ_DEMO"));
        System.out.println("String value="+SeqUtil.getString("SEQ_DEMO"));
        System.out.println("String 8 value="+SeqUtil.getString("SEQ_DEMO",8));
    }

    @Test
    public void cacheTest(){
        CacheUtil.addItem("fangyftest",1);
        System.out.println("fangyftest="+CacheUtil.getItem("fangyftest"));
    }
}