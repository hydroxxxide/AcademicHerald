package com.example.academicherald.config;

import com.example.academicherald.filter.JwtRequestFilter;
import com.example.academicherald.security.DetailsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final DetailsUserService detailsUserService;
    private final JwtRequestFilter jwtRequestFilter;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @Autowired
    public SecurityConfig(DetailsUserService detailsUserService, JwtRequestFilter jwtRequestFilter, OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler, OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler) {
        this.detailsUserService = detailsUserService;
        this.jwtRequestFilter = jwtRequestFilter;
        this.oAuth2AuthenticationSuccessHandler = oAuth2AuthenticationSuccessHandler;
        this.oAuth2AuthenticationFailureHandler = oAuth2AuthenticationFailureHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsUserService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .addFilterBefore(new CorsFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
//                .antMatchers("/admin/**").permitAll()
//                .antMatchers("/manager/**").hasRole("MANAGER")
//                .antMatchers("/mentor/**").hasRole("MENTOR")
//                .antMatchers("/mentor/**").hasRole("REVIEWER")
//                .antMatchers("/student/**").hasRole("STUDENT")
//                .antMatchers("/user/**").permitAll()
//                .antMatchers("/user/**").hasRole("ADMIN")
//                .antMatchers("/user/**").hasRole("MENTOR")
//                .antMatchers("/user/**").hasRole("REVIEWER")
//                .antMatchers("/user/**").hasRole("STUDENT")
//                .antMatchers("/user/**").hasRole("GUEST")
                .and()
                .logout()
                .and()
                .csrf().disable();

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
