package com.ai.bdex.dataexchange.apigateway;

import com.ai.paas.util.Utils;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * @author yafei
 * @since 2017/4/4
 */
@ImportResource({"classpath:/dubbo/*.xml"})
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@MapperScan("com.ai.bdex.dataexchange.apigateway.dao.mapper")
public class ApiGatewayServiceStart {
    private static Logger logger = LoggerFactory.getLogger(ApiGatewayServiceStart.class);

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = SpringApplication.run(ApiGatewayServiceStart.class, args);
        Utils.setCtx(context);

        logger.error("ApiGatewayServiceStart 启动成功...");
    }

}
