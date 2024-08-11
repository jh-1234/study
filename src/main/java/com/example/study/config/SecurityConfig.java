package com.example.study.config;

import com.example.study.security.LoginFailHandler;
import com.example.study.security.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 해당 Config 파일이 스프링 시큐리티에서도 관리가 된다.
public class SecurityConfig {

    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }

    @Bean
    public LoginFailHandler loginFailHandler() {
        return new LoginFailHandler();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/loginProc", "/loginValid", "/join", "/joinProc", "/usernameOverlapCheck", "/css/**").permitAll() // 모든 접근 허용
                        .requestMatchers("/").hasRole("USER")
                        .requestMatchers("/manager").hasRole("MANAGER")
                        .requestMatchers("/admin").hasRole("ADMIN") // ADMIN 권한이 있는 사용자만 접근 허용
                        .anyRequest().authenticated()  // 나머지 모든 경로는 로그인 된 사용자만 접근 허용
                )
                .formLogin(login -> login
                        .loginPage("/login") // 로그인페이지 직접 지정
                        .loginProcessingUrl("/loginProc") // 로그인 form 정보 목적지 경로
                        .successHandler(loginSuccessHandler())
                        .failureHandler(loginFailHandler())
//                        .defaultSuccessUrl("/", true) // 로그인 성공 시 보여줄 페이지
                )
                .sessionManagement(session -> session
                        .maximumSessions(1) // 하나의 아이디에 다중 로그인 허용 갯수 설정
                        .maxSessionsPreventsLogin(false) // true : 허용 갯수 초과 시 새로운 로그인 차단, false : 허용 갯수 초과 시 기존 세션 하나 삭제
                )
                .sessionManagement(session -> session // 세션 고정 공격 방지 로직
                        .sessionFixation().changeSessionId() // 로그인 시 동일한 세션에 대한 ID 변경
                )
                .logout(out -> out
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                );

        return http.build();
    }

    // hasRole 설정을 더 간편하게
    @Bean
    public RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.fromHierarchy("ROLE_ADMIN > ROLE_MANAGER > ROLE_USER");
    }
}
