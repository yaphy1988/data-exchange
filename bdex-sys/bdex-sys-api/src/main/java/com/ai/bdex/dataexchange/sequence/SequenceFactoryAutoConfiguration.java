package com.ai.bdex.dataexchange.sequence;

import com.ai.paas.config.ConfigurationCenter;
import com.ai.paas.config.autoconfigure.ConfigCenterAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;


/**
 * Created by fangyunfeng on 2017/4/17.
 */
@Configuration
@ConditionalOnBean(ConfigurationCenter.class)
@AutoConfigureAfter(ConfigCenterAutoConfiguration.class)

public class SequenceFactoryAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(SequenceFactoryAutoConfiguration.class);

    @Value("${application.sequencefactory.confpath}")
    private String sequenceFactoryConfPath;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Bean
    @Lazy(true)
    @ConditionalOnMissingBean
    public SequenceFactory sequenceManager(ConfigurationCenter configCenter) throws Exception {
        SimpleSequenceFactory sequenceFactory = new SimpleSequenceFactory();
        sequenceFactory.setConfCenter(configCenter);
        sequenceFactory.setConfPath("/com/ai/db/bdex/sequence/conf");
        sequenceFactory.initialize();
        logger.info("SequenceFactoryAutoConfiguration create bean SequenceFactory success!");
        logger.info("SequenceFactory use confPath is : {}", sequenceFactoryConfPath);
        return sequenceFactory;
    }
}
