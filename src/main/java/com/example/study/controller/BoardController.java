package com.example.study.controller;

import com.example.study.dto.BoardDTO;
import com.example.study.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")
    public String board() {
        return "/board/board";
    }

    @GetMapping("/boardRead/{id}")
    public String boardRead(@PathVariable int id, Model model) {
        return "/board/boardRead";
    }

    @GetMapping("/boardWrite")
    public String boardWrite(Model model) {
        model.addAttribute("board", new BoardDTO());

        return "/board/boardWrite";
    }

    @PostMapping("/boardWrite")
    public String boardWrite(@Valid BoardDTO boardDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/board/boardWrite";
        }

        long num = boardService.boardWrite(boardDTO);

        return "redirect:/board/boardRead/" + num;
    }
}
