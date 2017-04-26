package com.ai.bdex.dataexchange.billingcenter;

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
@ImportResource({"classpath:/dubbo/dubboContext.xml"})
@SpringBootApplication
@MapperScan("com.ai.bdex.dataexchange.billingcenter.dao.mapper")
public class BillingCenterServiceStart {
    private static Logger logger = LoggerFactory.getLogger(BillingCenterServiceStart.class);

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = SpringApplication.run(BillingCenterServiceStart.class, args);
        Utils.setCtx(context);

        logger.error("BillingCenterServiceStart 启动成功...");
    }

}
