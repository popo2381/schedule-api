package com.popo2381.scheduleapi.controller;

import com.popo2381.scheduleapi.dto.CommentRequest;
import com.popo2381.scheduleapi.dto.CommentResponse;
import com.popo2381.scheduleapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CommentResponse> createComment(@PathVariable Long scheduleId, @RequestBody CommentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(scheduleId, request));
    }
}
