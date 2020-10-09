package br.cicerosnt.project3.config;

import br.cicerosnt.project3.service.CustomAdministratorDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.beans.Encoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAdministratorDetailService customAdministratorDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/*/protected/**").hasRole("USER")
                .antMatchers("/*/admin/**").hasRole("ADMIN")
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customAdministratorDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        PasswordEncoder encoder = PasswordEncoderFactories
//                .createDelegatingPasswordEncoder();
//
//        auth.inMemoryAuthentication()
//                .withUser("cicerosnt")
//                .password(encoder.encode("0ias"))
//                .roles("USER")
//                .and()
//                .withUser("admin")
//                .password(encoder.encode("0ias"))
//                .roles("USER","ADMIN");
//    }
}
