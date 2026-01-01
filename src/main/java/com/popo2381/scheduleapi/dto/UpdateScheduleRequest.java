package com.popo2381.scheduleapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateScheduleRequest {
    @NotBlank(message = "제목을 입력해주세요.")
    @Size(max = 30, message = "제목은 최대 30자 이내로 입력해주세요")
    private String title;

    @NotBlank(message = "작성자명을 입력해주세요.")
    private String writer;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
