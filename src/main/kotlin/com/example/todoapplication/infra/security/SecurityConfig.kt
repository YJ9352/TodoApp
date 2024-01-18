package com.example.todoapplication.infra.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig(
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers(
                    "/api/user/signin",
                    "/api/user/signup",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                )
                .permitAll()
                .anyRequest().authenticated()
            }
//            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
//            .exceptionHandling{
//                it.authenticationEntryPoint(authenticationEntrypoint)
//                it.accessDeniedHandler(accessDeniedHandler)
//            }
            .build()
    }
}