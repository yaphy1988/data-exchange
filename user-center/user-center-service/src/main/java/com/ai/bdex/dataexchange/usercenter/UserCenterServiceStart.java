package com.ai.bdex.dataexchange.usercenter;

import com.ai.paas.util.Utils;
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
@ImportResource({"/spring/*-db-config.xml"})
@SpringBootApplication
public class UserCenterServiceStart {
    private static Logger logger = LoggerFactory.getLogger(UserCenterServiceStart.class);

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = SpringApplication.run(UserCenterServiceStart.class, args);
        Utils.setCtx(context);

        logger.error("UserCenterServiceStart 启动成功...");
    }

}
