package com.example.study.service;

import com.example.study.dto.BoardDTO;
import com.example.study.entity.Board;
import com.example.study.repository.BoardRepository;
import com.example.study.security.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;

    public long boardWrite(BoardDTO boardDTO) {
        Board board = new Board();
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        board.setAuthor(Session.getUser().getUsername());
        board.setViewCnt(0);
        board.setRecommendCnt(0);
        boardRepository.save(board);

        return board.getId();
    }
}
