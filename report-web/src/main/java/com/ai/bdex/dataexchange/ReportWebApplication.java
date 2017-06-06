package com.ai.bdex.dataexchange;

import com.ai.bdex.dataexchange.report.entity.UserStatisticQueryInfo;
import com.ai.bdex.dataexchange.report.service.interfaces.IReportSV;
import com.ai.paas.util.Utils;
import com.alibaba.boot.dubbo.annotation.EnableDubboConfiguration;
import com.github.pagehelper.Page;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableDubboConfiguration
@MapperScan("com.ai.bdex.dataexchange.report.dao.mapper")
public class ReportWebApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ReportWebApplication.class, args);
        Utils.setCtx(context);

        Page<UserStatisticQueryInfo> page = Utils.getInstance(IReportSV.class).getRePortData("authStaffSign_DetailReport",new UserStatisticQueryInfo());

        System.out.println("-----------------恭喜你启动ReportWeb成功了！-----------------");
    }
}
