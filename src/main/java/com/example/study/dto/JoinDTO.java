package com.example.study.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JoinDTO {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    private String email;

    @NotBlank
    private String gender;

    private String birthdate;

    private String address;

    @NotBlank
    private String phone;
}
