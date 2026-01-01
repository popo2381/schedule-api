package com.popo2381.scheduleapi.dto;
import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    private String title;
    private String content;
    private String writer;
    private String password;
}
