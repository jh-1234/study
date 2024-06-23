package com.example.study.controller;

import com.example.study.dto.JoinDTO;
import com.example.study.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @GetMapping("/join")
    public String joinPage() {
        return "/login/join";
    }

    @PostMapping("/joinProc")
    public String joinProc(JoinDTO joinDTO) {
        joinService.joinProcess(joinDTO);

        return "redirect:/login";
    }
}
