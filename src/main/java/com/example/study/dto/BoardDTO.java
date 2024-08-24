package com.example.study.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private String author;

    private Integer viewCnt;

    private Integer recommendCnt;
}
