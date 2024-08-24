package com.example.study.security;

import com.example.study.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class Session {

    public static User getUser() {
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUser();
    }
}
