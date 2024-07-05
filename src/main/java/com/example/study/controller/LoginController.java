package com.example.study.controller;

import com.example.study.dto.LoginDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());

        return "/login/login";
    }

    @PostMapping("/loginValid")
    public String loginValid(@Valid LoginDTO loginDTO, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (bindingResult.hasErrors()) {
            return "/login/login";
        }

        request.getRequestDispatcher("/loginProc").forward(request, response);

        return null;
    }
}
