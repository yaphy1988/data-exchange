package com.ai.bdex.dataexchange.aipcenter;

import com.ai.paas.util.Utils;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * @author yafei
 * @since 2017/4/4
 */
@ImportResource({"classpath:/dubbo/service/*.xml","classpath:/spring/spring-aop-transaction.xml"})
@SpringBootApplication
@MapperScan("com.ai.bdex.dataexchange.aipcenter.dao.mapper")
public class AipCenterServiceStart {
    private static Logger logger = LoggerFactory.getLogger(AipCenterServiceStart.class);

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = SpringApplication.run(AipCenterServiceStart.class, args);
        Utils.setCtx(context);

        logger.error("ApiCenterServiceStart 启动成功...");
    }

}
