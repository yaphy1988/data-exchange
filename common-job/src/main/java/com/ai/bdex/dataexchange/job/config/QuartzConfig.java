package com.ai.bdex.dataexchange.job.config;

import com.ai.bdex.dataexchange.job.service.QuartzScheduleService;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by yaphy on 2017/6/1.
 */
@Configuration
public class QuartzConfig {

    @Value("${spring.datasource.driver-class-name}")
    String quartzDataSourceDriver;

    @Value("${spring.datasource.url}")
    String quartzDataSourceURL;

    @Value("${spring.datasource.username}")
    String quartzDataSourceUsername;

    @Value("${spring.datasource.password}")
    String quartzDataSourcePassword;

    @Bean
    public Scheduler scheduler() throws IOException, SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory(quartzProperties());
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        return scheduler;
    }

    @Bean
    public QuartzScheduleService quartzScheduleService() {
        QuartzScheduleService quartzScheduleService = new QuartzScheduleService();
        try {
            quartzScheduleService.setScheduler(scheduler());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return quartzScheduleService;
    }

    /**
     * 设置quartz属性
     * @throws IOException
     */
    public Properties quartzProperties() throws IOException {
        Properties prop = new Properties();
        /**
         * Configure Main Scheduler Properties
         */
        prop.put("quartz.scheduler.instanceName", "ServerScheduler");
        prop.put("org.quartz.scheduler.instanceId", "AUTO");
        prop.put("org.quartz.scheduler.skipUpdateCheck", "true");

        /**
         * Configure JobStore
         */
        prop.put("org.quartz.jobStore.misfireThreshold", "60000");
        prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        prop.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
        prop.put("org.quartz.jobStore.dataSource", "quartzDataSource");
        prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
        prop.put("org.quartz.jobStore.isClustered", "true");
        prop.put("org.quartz.jobStore.clusterCheckinInterval", "20000");

        /**
         * Configure ThreadPool
         */
        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        prop.put("org.quartz.threadPool.threadCount", "5");

        /**
         * Configure Datasources
         */
        prop.put("org.quartz.dataSource.quartzDataSource.driver", quartzDataSourceDriver);
        prop.put("org.quartz.dataSource.quartzDataSource.URL", quartzDataSourceURL);
        prop.put("org.quartz.dataSource.quartzDataSource.user", quartzDataSourceUsername);
        prop.put("org.quartz.dataSource.quartzDataSource.password", quartzDataSourcePassword);
        prop.put("org.quartz.dataSource.quartzDataSource.maxConnections", "10");
        return prop;
    }
}
