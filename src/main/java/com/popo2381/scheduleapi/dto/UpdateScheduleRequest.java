package com.popo2381.scheduleapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateScheduleRequest {
    @NotBlank(message = "제목은 필수 입력 값입니다.")
    @Size(max = 30, message = "제목은 최대 30자 이내입니다.")
    private String title;

    @NotBlank(message = "작성자명은 필수 입력 값입니다.")
    private String writer;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;
}
