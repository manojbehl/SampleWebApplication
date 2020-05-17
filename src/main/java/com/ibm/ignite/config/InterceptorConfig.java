package com.ibm.ignite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{

	@Autowired
	AutheticationInterceptor securityInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		System.err.println("inter");

		registry.addInterceptor(securityInterceptor).addPathPatterns("/api/**").excludePathPatterns("/forgot/password**",
			"/login","/ecomm","orderhistory",	"/resend/otp","/h2**/**","/favicon.ico" , "/qrcode/**","/generateOTP/**", "/h2-console/**","/swagger**/**", "/swagger-ui.html", "/webjars/**", "/v2/api-docs", "/user/create");
	}
}
