package com.gopal.patient.config;

import com.gopal.patient.service.impl.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
     @Autowired
     private JwtAuthFilter jwtAuthFilter;

     @Autowired
     private MyUserDetailService myUserDetailService;
    // to customize security FilterChain  created a bean that gives the object of SecurityFilterChain which
    //will be provided by Httpsecurity
    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {
       return  http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated())
               .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
               .build();
    }
//    @Bean
//    public UserDetailsService myUserDetailsService() {
//
//        UserDetails user1 = User
//                .withUsername("gopal")
//                .password(passwordEncoder().encode("abc@123"))
//                .roles("admin")
//                .build();
//        return new InMemoryUserDetailsManager(user1);
//
//
//    }
    //Spring Security does not auto-expose an AuthenticationManager bean in the context unless you explicitly define it.
    //
    //So, when you try to use @Autowired AuthenticationManager in your controller or service (like in JWT login),
    // Spring needs to know where to get it from — and that's why you define this bean:

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
