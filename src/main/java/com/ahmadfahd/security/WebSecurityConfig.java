package com.ahmadfahd.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled"
                        + " from users where username=?")
                .authoritiesByUsernameQuery("select username, rolename "
                        + "from roles where username=?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*
        * @PreAurhorize
        * */
                http.csrf().disable()
                        .httpBasic()
                        .and()
                        .authorizeRequests()
                        . antMatchers("/users/**","/users").permitAll()
                .anyRequest().authenticated();


        /*
        *  Default security
        *  */

//        http.authorizeRequests()
//                .antMatchers("/**/**/viewall","/viewdeleted").hasRole("ADMIN") // ادمن فقط
//                .antMatchers("/view/","/book").hasAnyRole("ADMIN", "USER") // يوزر وادمن
//                .antMatchers("/welcome").hasRole("USER") // لليوزر فقط
//                .antMatchers("/login","/reg").permitAll() // صفحات ببلك
//                .anyRequest().authenticated()
//                .and().httpBasic(); // Authenticate users with HTTP basic authentication

    }
}