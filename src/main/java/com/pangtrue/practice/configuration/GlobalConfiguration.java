package com.pangtrue.practice.configuration;

import com.pangtrue.practice.configuration.interceptor.ControllerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 3. 16.
 * Time: 오전 10:45
 */
@Slf4j
@Configuration
public class GlobalConfiguration extends WebMvcConfigurationSupport {

    private static final String[] EXCLUDE_RESOURCE_PATH_PATTERNS = {
            "/images/**",
            "/js/**",
            "/css/**"
    };

    @Value("${spring.mvc.static-path-pattern:/**}")
    private String DEFAULT_RESOURCE_PATH;

    private static final String DEFAULT_RESOURCE_LOCATION = "classpath:/static/";

    public GlobalConfiguration() {
        log.info("--> Custom WebMvcConfigurationSupport start.");
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(pageableHandlerMethodArgumentResolver());
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(controllerInterceptor())
                .addPathPatterns("/*")
                .excludePathPatterns(EXCLUDE_RESOURCE_PATH_PATTERNS);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        ResourceHandlerRegistration resourceHandlerRegistration = registry.addResourceHandler(DEFAULT_RESOURCE_PATH);
        resourceHandlerRegistration.addResourceLocations(DEFAULT_RESOURCE_LOCATION);
        resourceHandlerRegistration.setCachePeriod(0);
    }

    @Override
    protected void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public ControllerInterceptor controllerInterceptor() {
        return new ControllerInterceptor();
    }

    private PageableHandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver() {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setFallbackPageable(PageRequest.of(1, 10));
        resolver.setOneIndexedParameters(false);

        return resolver;
    }
}
