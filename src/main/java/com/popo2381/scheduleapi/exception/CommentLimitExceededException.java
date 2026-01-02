package com.popo2381.scheduleapi.exception;
/**
 * 일정당 댓글 최대 개수(10개)를 초과했을 때 발생하는 예외
 */
public class CommentLimitExceededException extends RuntimeException {

    public CommentLimitExceededException() {
        super("댓글 개수를 초과했습니다. 일정당 최대 10개까지만 작성 가능합니다.");
    }
}