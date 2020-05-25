package com.megvii.distributed.zuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
public class ResourceServerConfig {

    public static final String RESOURCE_ID = "res1";

    /**
     * 统一认证服务(UAA) 资源拦截
     */
    @Configuration
    @EnableResourceServer
    public class UAAServerConfig extends ResourceServerConfigurerAdapter{

        @Autowired
        TokenStore tokenStore;

        @Override
        public void configure(ResourceServerSecurityConfigurer resourceServerSecurityConfigurer) throws Exception {
            resourceServerSecurityConfigurer
                    .tokenStore(tokenStore)
                    .resourceId(RESOURCE_ID)
                    .stateless(true);
        }

        @Override
        public void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.authorizeRequests()
                    .antMatchers("/uaa/**").permitAll();
        }
    }

    @Configuration
    @EnableResourceServer
    public class OrderServerConfig extends ResourceServerConfigurerAdapter{

        @Autowired
        TokenStore tokenStore;

        @Override
        public void configure(ResourceServerSecurityConfigurer resourceServerSecurityConfigurer) throws Exception {
            resourceServerSecurityConfigurer
                    .resourceId(RESOURCE_ID)
                    .tokenStore(tokenStore)
                    .stateless(true);
        }

        @Override
        public void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.authorizeRequests()
                    .antMatchers("/order/**").access("#oauth2.hasScope('ROLE_ADMIN')");
        }
    }

}
