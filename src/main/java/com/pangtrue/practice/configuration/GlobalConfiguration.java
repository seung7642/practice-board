package com.pangtrue.practice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 3. 16.
 * Time: 오전 10:45
 */
@Configuration
@EnableWebMvc // 해당 어노테이션을 선언함으로써 WebMvcAutoConfiguration이 비활성화된다.
public class GlobalConfiguration {
}
