package com.popo2381.scheduleapi.dto;

import lombok.Getter;

import java.time.LocalDateTime;
/**
 * 댓글 생성/수정 응답 DTO
 */
@Getter
public class CommentResponse {
    private final Long id;
    private final String content;
    private final String writer;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CommentResponse(Long id, String content, String writer, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
