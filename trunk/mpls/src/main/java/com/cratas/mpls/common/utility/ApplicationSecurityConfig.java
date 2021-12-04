package com.cratas.mpls.common.utility;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * 
 * @author mukesh
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( securedEnabled = true )
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
	
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	             // http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true);
			      http.authorizeRequests().anyRequest().authenticated()
		            .and()
		            .formLogin()
		            .loginPage("/")
		            .usernameParameter("loginId")
		            .permitAll()
		            .and()
		            .logout()
		            .logoutSuccessUrl("/user/logout")
		            .and()
		            .sessionManagement()
		            .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
		            .sessionFixation()
		            .migrateSession()
		            .maximumSessions(1)
		            .maxSessionsPreventsLogin(true);
	    }

}
