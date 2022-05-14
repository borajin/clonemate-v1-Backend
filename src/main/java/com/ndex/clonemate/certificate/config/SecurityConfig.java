package com.ndex.clonemate.certificate.config;

import com.ndex.clonemate.certificate.handler.LoginFailureHandler;
import com.ndex.clonemate.certificate.handler.LoginSuccessHandler;
import com.ndex.clonemate.certificate.provider.CustomAuthenticationProvider;
import com.ndex.clonemate.certificate.filter.CustomAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/users", "/login**", "/users/email/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(customAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);

        http.formLogin()
                .disable();

        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter(
                new AntPathRequestMatcher("/login", HttpMethod.POST.name())
        );
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(new LoginSuccessHandler());
        filter.setAuthenticationFailureHandler(new LoginFailureHandler());

        return filter;
    }
}
