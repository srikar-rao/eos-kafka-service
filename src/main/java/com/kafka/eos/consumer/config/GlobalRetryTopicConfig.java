package com.kafka.eos.consumer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.retrytopic.RetryTopicConfigurationSupport;

@Configuration
public class GlobalRetryTopicConfig extends RetryTopicConfigurationSupport {

    @Override
    protected void configureCustomizers(CustomizersConfigurer customizersConfigurer){
        customizersConfigurer.customizeDeadLetterPublishingRecoverer(dlpr -> {
            dlpr.setAppendOriginalHeaders(true);
            dlpr.setStripPreviousExceptionHeaders(false);
        });
    }
}
