package org.gt.shipping.cargo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceServiceConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .permitAll()
                //.antMatchers(HttpMethod.DELETE, "/v1/shipping/**")
                //.hasRole("ADMIN")
                .anyRequest()
                .authenticated();
    }
}
