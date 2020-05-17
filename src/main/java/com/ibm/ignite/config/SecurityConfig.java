package com.ibm.ignite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter.XFrameOptionsMode;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ibm.ignite.service.MyUserDetailService;

@Configuration
@EnableWebSecurity
@DependsOn(value= {"interceptorConfig"})
public class SecurityConfig  extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

	@Bean
	public MyUserDetailService myUserDetailService(){
		return new MyUserDetailService();
	}

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.headers().addHeaderWriter( new XFrameOptionsHeaderWriter( new
	 * WhiteListedAllowFromStrategy(Arrays.asList("localhost")))); }
	 */
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.authenticationProvider(authenticationProvider());
		
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Bean
	@Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
        
    }
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable().anonymous();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider
	      = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(myUserDetailService());
	    authProvider.setPasswordEncoder( encoder());
	    return authProvider;
	}
	 
	@Bean
	public PasswordEncoder encoder() {
        return new PasswordEncoder() {

            @Override

            public String encode(CharSequence rawPassword) {

                return rawPassword.toString();

            }

            @Override

            public boolean matches(CharSequence rawPassword, String encodedPassword) {

                return rawPassword.toString().equals(encodedPassword);

            }

        };
	}
	
	
	/*
	 * @Bean public ServletRegistrationBean servletRegistrationBean(){ return new
	 * ServletRegistrationBean(new ImageServlet(),"/images/*"); }
	 * 
	 * 
	 * @Bean public FilterRegistrationBean<CorsFilter> loggingFilter() {
	 * FilterRegistrationBean<CorsFilter> registrationBean = new
	 * FilterRegistrationBean<CorsFilter>(); registrationBean.setFilter(new
	 * CorsFilter()); registrationBean.addUrlPatterns("/*");
	 * 
	 * return registrationBean; }
	 */
}
