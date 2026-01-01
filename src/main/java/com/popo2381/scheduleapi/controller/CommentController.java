package com.popo2381.scheduleapi.controller;

import com.popo2381.scheduleapi.dto.CommentRequest;
import com.popo2381.scheduleapi.dto.CommentResponse;
import com.popo2381.scheduleapi.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CommentResponse> createComment(@PathVariable Long scheduleId, @Valid @RequestBody CommentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(scheduleId, request));
    }
    @PutMapping("/schedules/{scheduleId}/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long scheduleId, @PathVariable Long commentId, @Valid @RequestBody CommentRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.update(scheduleId,commentId,request));
    }
}
