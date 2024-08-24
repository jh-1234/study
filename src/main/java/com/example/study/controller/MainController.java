package com.example.study.controller;

import com.example.study.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Iterator;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        GrantedAuthority authority = authentication.getAuthorities().iterator().next();
//
//        String id = authentication.getName();
//        String role = authority.getAuthority();
//        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();

        return "/home/home";
    }
}
