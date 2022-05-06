package com.example.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

  @Autowired
  private LoginInterceptor loginInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
//    InterceptorRegistration ir = registry.addInterceptor(loginInterceptor);

//    ir.addPathPatterns("/**");

//    ir.excludePathPatterns("/login/register");
//    ir.excludePathPatterns("/login/login");
//    ir.excludePathPatterns("/upload/test");

  }
}
