package com.project.FileChecker.config;


import com.project.FileChecker.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.project.FileChecker.config.JWTAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public class MySecurityConfig {

            @Autowired
            private UserDetailsServiceImpl userDetailsServiceImpl;
           @Autowired
            private JWTAuthenticationEntryPoint unauthorizedHandler;
            @Autowired
            private JwtAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

            @Bean
            public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
            }


            @Bean
            public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                        .csrf(csrf -> csrf.disable())
                        .cors(cors->cors.disable())
                        .authorizeHttpRequests((auth) ->
                                {
                                    auth.requestMatchers("/generate-token", "/student/").permitAll();
                                auth.requestMatchers(HttpMethod.OPTIONS).permitAll();
                                auth.anyRequest().authenticated();})

                        .sessionManagement(ses->ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS
                        ));

                http.exceptionHandling((exception)-> exception.authenticationEntryPoint(unauthorizedHandler));
                http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
            }

//            @Bean
//            public WebMvcConfigurer corsConfigurer() {
//                return new WebMvcConfigurer() {
//                    @Override
//                    public void addCorsMappings(CorsRegistry registry) {
//                        registry.addMapping("/**")
//                                .allowedMethods("*");
//                    }
//                };
//            }



        }

