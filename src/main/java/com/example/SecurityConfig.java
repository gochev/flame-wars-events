package com.example;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.model.Organizer;
import com.example.repository.OrganizerRepository;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public ApplicationContext context;

    @Autowired
    private OrganizerRepository organizerRepository;
    
    @Override
    public void configure(final WebSecurity web) throws Exception {
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        
    		auth.authenticationProvider(new AuthenticationProvider() {
	            @Override
	            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	                String username = (String) authentication.getPrincipal();
	                String providedPassword = (String) authentication.getCredentials();
	                
	                Organizer user = organizerRepository.findUserByUsername(username);
	                
	                if (user == null || !passwordEncoder().matches(providedPassword, user.getPassword())) {
	                    throw new BadCredentialsException("Username/Password does not match for " + authentication.getPrincipal());
	                }
	                
	                return new UsernamePasswordAuthenticationToken(username, providedPassword, Collections.singleton(new SimpleGrantedAuthority("ORGANIZER")));
	            }

	            @Override
	            public boolean supports(Class<?> authentication) {
	                return true;
	            }
	        });
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/login", "/404").permitAll() // #4
                .antMatchers(HttpMethod.GET, "/events/**", "/agenda/**", "/talks/**").permitAll()
                .antMatchers(HttpMethod.POST, "/registrations/**","/submissions/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/talks/**").hasAuthority("SPEAKER") // #6
                .antMatchers("/events/**", "/talks/**").hasAuthority("ORGANIZER") // #6
//                .antMatchers("/user/**").hasAuthority("USER") //will contain schedule and etc
                .anyRequest().authenticated() // 7
        		.and()
        		.httpBasic();
        	
//                .and()
//                .formLogin()  // #8
//                .loginPage("/login") // #9
//                .permitAll(); // #5
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
