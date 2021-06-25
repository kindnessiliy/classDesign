package com.classdesign.config;

import com.classdesign.service.LoginService;
import com.classdesign.utils.PasswordCoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

/**
 * @author:zyh
 * @Time:2021-05-30-18:33
 * @email:1269231889@qq.com
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginService adminService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordCoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        //关闭csrf
        http
                .csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/user/**").access("hasRole('USER') or hasRole('ADMIN')")
                .antMatchers("/book/find").access("hasRole('USER') or hasRole('ADMIN')")
                .antMatchers("/book/detail").access("hasRole('USER') or hasRole('ADMIN')")
                .antMatchers("/borrow/**").access("hasRole('USER') or hasRole('ADMIN')")
                .antMatchers("/scan/**").hasRole("ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/auth/form")
                .defaultSuccessUrl("/user/hello", true)
                .permitAll();
        http
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/success")
                .logoutSuccessHandler(new SimpleUrlLogoutSuccessHandler());
    }
}
