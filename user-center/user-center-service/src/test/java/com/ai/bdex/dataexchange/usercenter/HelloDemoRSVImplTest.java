package com.ai.bdex.dataexchange.usercenter;

import com.ai.bdex.dataexchange.usercenter.dao.mapper.DemoMapper;
import com.ai.bdex.dataexchange.usercenter.dao.model.Demo;
import com.ai.paas.sequence.SeqUtil;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.DemoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IDemoRSV;
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
public class HelloDemoRSVImplTest {

    @Autowired
    private IDemoRSV demoRSV;

    @Autowired
    private DemoMapper demoMapper;
	
    @Test
    public void helloWorld() throws Exception {
        Demo demo = new Demo();
        demo.setId(SeqUtil.getInt("SEQ_DEMO"));
        //demo.setId(10081);
        demo.setUserName("方云峰");
        demo.setAddr("广西");
        demoMapper.insert(demo);
    }

    @Test
    public void queryDemo(){
        Demo demo = demoMapper.selectByPrimaryKey(10085);
        System.out.println("name="+demo.getUserName()+",addr="+demo.getAddr());
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