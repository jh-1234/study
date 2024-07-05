package com.example.study.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Setter
@Getter
public class JoinDTO {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank
    private String gender;

    @NotNull
    @Range(min = 1900, max = 2100)
    private Integer b_year;

    @NotNull
    @Range(min = 1, max = 12)
    private Integer b_month;

    @NotNull
    @Range(min = 1, max = 31)
    private Integer b_day;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private String birthDay;

    @NotBlank
    private String p_1;

    @NotBlank
    private String p_2;

    @NotBlank
    private String p_3;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private String phone;

    public LocalDate getBirthDay() {
        if (b_year == null) {
            this.b_year = 1900;
        }

        if (b_month == null) {
            this.b_month = 1;
        }

        if (b_day == null) {
            this.b_day = 1;
        }

        return LocalDate.of(this.b_year, this.b_month, this.b_day);
    }

    public String getPhone() {
        return p_1 + "-" + p_2 + "-" + p_3;
    }
}
