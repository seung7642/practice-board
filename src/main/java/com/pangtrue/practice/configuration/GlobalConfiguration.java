package com.pangtrue.practice.configuration;

import com.pangtrue.practice.configuration.interceptor.ControllerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

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

    public GlobalConfiguration() {
        log.info("--> Custom WebMvcConfigurationSupport start.");
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ControllerInterceptor())
                .addPathPatterns("/*")
                .excludePathPatterns(EXCLUDE_RESOURCE_PATH_PATTERNS);
    }
}
