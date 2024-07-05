package com.example.study.entity;

import com.example.study.constant.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @Column(length = 1)
    private String gender;

    @NotNull
    private LocalDate birthDay;

    @NotBlank
    private String phone;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
