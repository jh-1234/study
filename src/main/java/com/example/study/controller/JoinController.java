package com.example.study.controller;

import com.example.study.dto.JoinDTO;
import com.example.study.service.JoinService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @GetMapping("/join")
    public String joinPage(Model model) {
        model.addAttribute("joinDTO", new JoinDTO());

        return "/login/join";
    }

    @GetMapping("/usernameOverlapCheck")
    @ResponseBody
    public String usernameOverlapCheck(String username) {
        return joinService.existsByUsername(username) ? "아이디가 중복되었습니다." : "사용가능한 아이디입니다.";
    }

    @PostMapping("/joinProc")
    public String joinProc(@Valid JoinDTO joinDTO, BindingResult bindingResult) {
        if (bindingResult.getFieldErrors().stream().anyMatch(err -> err.getField().equals("b_year") || err.getField().equals("b_month") || err.getField().equals("b_day"))) {
            bindingResult.rejectValue("birthDay", "", "생년월일을 입력해주세요.");
        }

        if (bindingResult.getFieldErrors().stream().anyMatch(err -> err.getField().equals("p_1") || err.getField().equals("p_2") || err.getField().equals("p_3"))) {
            bindingResult.rejectValue("phone", "", "연락처를 입력해주세요.");
        }

        if (bindingResult.hasErrors()) {
            return "/login/join";
        }

        joinService.joinProcess(joinDTO);

        return "redirect:/login";
    }
}
