package com.example.fullCafe_spring_maven.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui.html").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers(PathRequest.toH2Console()).permitAll() // h2 콘솔허용
                        .anyRequest()
                        .authenticated()
                ) // 전부 인증
                //.addFilter(new TestFilter()) // Swagger-ui Authorization 검증용 테스트 필터
                .addFilter(new FirebaseAuthenticationFilter()) // firebase 검증 필터 추가
                .csrf(AbstractHttpConfigurer::disable) // csrf 및 세션 사용 안함
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(header -> {header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin);}) // h2 콘솔 프레임 관련 문제
                .build();
    }


}