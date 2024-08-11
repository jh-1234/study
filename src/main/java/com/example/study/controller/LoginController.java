package com.example.study.controller;

import com.example.study.dto.LoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) Integer errorCode, Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        model.addAttribute("errorCode", errorCode);

        return "/login/login";
    }
}
