package com.example.study.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    private String email;

    @NotBlank
    @Column(length = 1)
    private String gender;

    @NotNull
    private String birthdate;

    @NotBlank
    private String phone;

    private String address;

    private int passwordErrCnt;

    private String photoUrl;

//    @NotNull
//    @Enumerated(EnumType.STRING)
//    private UserRole role;
}
