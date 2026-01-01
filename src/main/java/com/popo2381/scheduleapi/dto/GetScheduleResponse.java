package com.popo2381.scheduleapi.dto;


import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetScheduleResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String writer;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final List<CommentResponse> comments;

    public GetScheduleResponse(Long id, String title, String content, String writer, LocalDateTime createdAt, LocalDateTime modifiedAt, List<CommentResponse> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.comments = comments;
    }
}
