package com.popo2381.scheduleapi.exception;

/**
 * 댓글이 존재하지 않거나,
 * 해당 일정에 소속되지 않은 댓글일 때 발생하는 예외
 */
public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException(Long commentId) {
        super("해당 댓글이 존재하지 않습니다. id=" + commentId);
    }
}