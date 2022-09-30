package com.project.connectdoctorpatient.configuration;

import com.opensymphony.sitemesh.webapp.SiteMeshFilter;
import com.project.connectdoctorpatient.filter.AuthenticationFilter;
import com.project.connectdoctorpatient.filter.CacheFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.project.connectdoctorpatient.constant.URL.AUTHENTICATION_URLS;

/**
 * @author sakib.khan
 * @since 2/27/22
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilter() {
        FilterRegistrationBean<AuthenticationFilter> authBean = new FilterRegistrationBean<>();
        authBean.setFilter(new AuthenticationFilter());
        authBean.addUrlPatterns(AUTHENTICATION_URLS);

        return authBean;
    }

    @Bean
    public FilterRegistrationBean<CacheFilter> cacheFilter() {
        FilterRegistrationBean<CacheFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CacheFilter());
        registrationBean.addUrlPatterns("*");

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<SiteMeshFilter> siteMeshFilter() {
        FilterRegistrationBean<SiteMeshFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SiteMeshFilter());
        registrationBean.addUrlPatterns("*");

        return registrationBean;
    }
}
