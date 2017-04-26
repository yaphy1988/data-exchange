//package com.ai.bdex.dataexchange.tradecenter;
//
//import com.ai.paas.sequence.SeqUtil;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.ImportResource;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.SortInfoRespDTO;
//import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.page.IPageInfoRSV;
//import com.ai.bdex.dataexchange.tradecenter.service.interfaces.IDemoSV;
//
//import java.util.List;
//
///**
// * @author yafei
// * @since 2017/3/31
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//@ImportResource({"classpath:/dubbo/dubboContext.xml"})
//public class HelloDemoRSVImplTest {
//	 @Autowired
//	    private IDemoSV demoRSV;
//	 @Autowired
//	    private IPageInfoRSV iPageInfoRSV;
//
//    @Test
//    public void helloWorld() throws Exception {
//    /*	DemoDTO sortInfoRespDTO = new DemoDTO();
//    	String demoinfo = demoRSV.callDemo(sortInfoRespDTO);
//    	System.out.print(demoinfo);*/
//     	SortInfoRespDTO sortInfoRespDTO = new SortInfoRespDTO();
//    	sortInfoRespDTO.setStatus("1");
//    	 //List<SortInfoRespDTO>  list = iPageInfoRSV.querySortInfos(sortInfoRespDTO);
//    	 //String aa = list.get(0).getSortLevel();
//		  int seq =  SeqUtil.getInt("SEQ_DATA_CUSTOMIZATION");
//
//    	 System.out.print("-------------SEQ_DATA_CUSTOMIZATION–Ú¡–------"+seq+"-----------------");
//     }
//
//     @Test
//     public void seqTest(){
//    	System.out.println(SeqUtil.getInt("SEQ_DEMO"));
//	 }
//
//}