package com.codeup.sequoiaspringbootblog;

import com.codeup.sequoiaspringbootblog.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private UserDetailsLoader userDetailsLoader;

    public SecurityConfiguration(UserDetailsLoader userDetailsLoader) {
        this.userDetailsLoader = userDetailsLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsLoader) // How to find users by their username
            .passwordEncoder(passwordEncoder()) // How to encode and verify passwords
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            /* Login configuration */
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/posts") // user's home page, it can be any URL
                .permitAll()
            .and()
                .logout()
                .logoutSuccessUrl("/login?logout") // append a query string value
            /* Pages that can be viewed without having to log in */
            .and()
                .authorizeRequests()
                .antMatchers("/", "/posts") // anyone can see the home and the ads pages
                .permitAll()
            /* Pages that require authentication */
            .and()
                .authorizeRequests()
                .antMatchers(
                    "/posts/create", // only authenticated users can create ads
                    "/posts/{id}/edit", // only authenticated users can edit ads
                    // We're adding this 2 lines in order to get the User from the security
                    // context in the AdController, otherwise `getPrincipal()` might return
                    // a string instead of a User
                    "/ads/new",
                    "/ads/create"
                )
                .authenticated()
        ;
    }
}
