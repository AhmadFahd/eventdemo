package com.ahmadfahd.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    // Custom JWT based security filter
    @Autowired
    JwtAuthorizationTokenFilter authenticationTokenFilter;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.route.authentication.path}")
    private String authenticationPath;

    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled"
                        + " from users where username=?")
                .authoritiesByUsernameQuery("select username, authority "
                        + "from authorities where username=?")
                .passwordEncoder(passwordEncoderBean());
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
            // we don't need CSRF because our token is invulnerable
//            .csrf().disable();
//
//            httpSecurity.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);
//                    .and()
////
//            // don't create session
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//
//            .and().authorizeRequests()
//
//            // Un-secure H2 Database
//            .antMatchers("/h2-console/**/**").permitAll()
//
//            .antMatchers("/auth/**").permitAll()
//            .anyRequest().authenticated();
////
//       httpSecurity
//            .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
//
//        // disable page caching
//        httpSecurity
//            .headers()
//            .frameOptions().sameOrigin()  // required to set for H2 else H2 Console will be blank.
//            .cacheControl();
////
//        httpSecurity
//                    .authorizeRequests()
//                    .antMatchers(HttpMethod.GET).hasRole("ADMIN")
////                    .antMatchers(HttpMethod.PUT).authenticated()
////                    .antMatchers(HttpMethod.DELETE).authenticated()
//                .and().addFilterBefore(authenticationTokenFilter,UsernamePasswordAuthenticationFilter.class);
/*Last JDBC*/
        httpSecurity.authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
                .and()
                .httpBasic(); // Authenticate users with HTTP basic authentication
    }

}
//
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        // AuthenticationTokenFilter will ignore the below paths
//        web
//            .ignoring()
//            .antMatchers(
//                HttpMethod.POST,
//                authenticationPath
//            )
//
//            // allow anonymous resource requests
//            .and()
//            .ignoring()
//            .antMatchers(
//                HttpMethod.GET,
//                "/",
//                "/*.html",
//                "/favicon.ico",
//                "/**/*.html",
//                "/**/*.css",
//                "/**/*.js"
//            )
//
//            // Un-secure H2 Database (for testing purposes, H2 console shouldn't be unprotected in production)
//            .and()
//            .ignoring()
//            .antMatchers("/h2-console/**/**");
//    }
//}
