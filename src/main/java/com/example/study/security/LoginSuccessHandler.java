package com.example.study.security;

import com.example.study.entity.User;
import com.example.study.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Configuration
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();
        System.out.println(username);

        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            if (user.get().getPasswordErrCnt() >= 5) {
                HttpSession session = request.getSession(false);

                if (Objects.nonNull(session)) {
                    session.invalidate();
                }

                response.sendRedirect("/login?errorCode=2");
            } else {
                response.sendRedirect("/");
            }
        }
    }
}
