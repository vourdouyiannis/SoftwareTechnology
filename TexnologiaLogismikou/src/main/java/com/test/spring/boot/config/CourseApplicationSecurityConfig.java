package com.test.spring.boot.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class CourseApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
    DataSource dataSource;
	
	
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception 
    {
    	auth.jdbcAuthentication().dataSource(dataSource);
    		
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
	
		.antMatchers("/*").hasAnyRole("USER", "ADMIN") 
		.antMatchers("/login*").permitAll()
		.anyRequest().authenticated()
		.and() 
		.formLogin(); 
	

	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
		
}






