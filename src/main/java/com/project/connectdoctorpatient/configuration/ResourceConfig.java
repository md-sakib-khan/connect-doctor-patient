package com.project.connectdoctorpatient.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author sakib.khan
 * @since 2/25/22
 */
@Configuration
public class ResourceConfig {

    @Bean
    public MessageSourceAccessor messageSourceAccessor() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("messages");
        source.setUseCodeAsDefaultMessage(true);

        return new MessageSourceAccessor(source);
    }
}
