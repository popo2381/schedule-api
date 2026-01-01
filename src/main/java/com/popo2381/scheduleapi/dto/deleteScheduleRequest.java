package com.popo2381.scheduleapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class deleteScheduleRequest {
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
