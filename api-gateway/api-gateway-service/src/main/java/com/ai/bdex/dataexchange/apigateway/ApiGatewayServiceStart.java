package com.ai.bdex.dataexchange.apigateway;

import com.ai.paas.util.Utils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author yafei
 * @since 2017/4/4
 */
@SpringBootApplication
public class ApiGatewayServiceStart {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ApiGatewayServiceStart.class, args);
        Utils.setCtx(context);
    }

}
