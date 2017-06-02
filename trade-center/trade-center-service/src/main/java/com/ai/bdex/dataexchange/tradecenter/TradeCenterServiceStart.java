package com.ai.bdex.dataexchange.tradecenter;

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
@ImportResource({"classpath*:/dubbo/service/*.xml","classpath:/spring/spring-aop-transaction.xml"})
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@MapperScan("com.ai.bdex.dataexchange.tradecenter.dao.mapper")
public class TradeCenterServiceStart {
    private static Logger logger = LoggerFactory.getLogger(TradeCenterServiceStart.class);

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = SpringApplication.run(TradeCenterServiceStart.class, args);
        Utils.setCtx(context);

        logger.error("恭喜你启动成功了！--TradeCenterServiceStart 启动成功...");
    }

}
