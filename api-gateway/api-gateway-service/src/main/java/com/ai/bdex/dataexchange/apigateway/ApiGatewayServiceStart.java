package com.ai.bdex.dataexchange.apigateway;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

//import com.ai.bdex.dataexchange.apigateway.dubbo.interfaces.IAipTransationRSV;
import com.ai.paas.util.Utils;

/**
 * @author yafei
 * @since 2017/4/4
 */
@ImportResource({"classpath:/dubbo/service/*.xml","classpath:/spring/spring-aop-transaction.xml"})
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@MapperScan("com.ai.bdex.dataexchange.apigateway.dao.mapper")
public class ApiGatewayServiceStart {
    private static Logger logger = LoggerFactory.getLogger(ApiGatewayServiceStart.class);

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = SpringApplication.run(ApiGatewayServiceStart.class, args);
        Utils.setCtx(context);

        logger.error("ApiGatewayServiceStart 启动成功...");
//        IAipTransationRSV bean=(IAipTransationRSV)Utils.getBean("aipTransationRSV");
//        try{
//        	Map<String, String> result= bean.createTransation("44", "1.0", getMap());
//        }catch(Exception e){
//        	logger.error("", e);
//        }
    }
    
//    public static Map<String,Object> getMap(){
//    	Map<String,Object> data=new HashMap<String,Object>();
// 		data.put("access_token", "4BC1E87FA08A68F2FE4B963BF941DD4B");
// 		data.put("personName", "张三");
// 		data.put("identityType", "0");
// 		data.put("identityNumber", "421081199304173997");
// 		return data;
//    }

}
