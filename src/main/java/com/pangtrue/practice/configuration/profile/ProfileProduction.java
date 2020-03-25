package com.pangtrue.practice.configuration.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 3. 25.
 * Time: 오후 1:52
 */
@Configuration
@Profile(value = "production")
@PropertySource({"classpath:application-production.yml"})
public class ProfileProduction {


}
