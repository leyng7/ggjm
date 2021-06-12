package me.ryeong.ggjm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers(
                        "/",
                        "/parties",
                        "/members/new",
                        "/js/**",
                        "/css/**",
                        "/images/**",
                        "/webjars/**",
                        "/hello/**",
                        "/gs-guide-websocket/**",
                        "/app/**",
                        "/topic/**"
                ).permitAll()
                .mvcMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated();

        http.formLogin();

        http.logout()
                .logoutSuccessUrl("/");

        http.httpBasic();
    }

}
