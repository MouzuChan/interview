package com.mouzu.project.interview.ratelimit;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/***
 * Description:限流拦截器配置
 *
 */
@Configuration
public class RateLimiterConfig implements WebMvcConfigurer {
    /**
     * 限流配置
     */
    @Resource
    private RateLimiterInterceptor rateLimiterInterceptor;

    /**
     * 限流配置
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rateLimiterInterceptor).addPathPatterns("/**");
    }

}
