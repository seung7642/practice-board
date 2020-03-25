package com.pangtrue.practice.configuration.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 3. 25.
 * Time: 오후 1:52
 */
@Import({ProfileDevelop.class, ProfileProduction.class})
@Configuration
public class ProfileConfiguration {


}
