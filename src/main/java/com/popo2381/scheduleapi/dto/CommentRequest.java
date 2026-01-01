package com.popo2381.scheduleapi.dto;

import lombok.Getter;

@Getter
public class CommentRequest {
    private String content;
    private String writer;
    private String password;
}
