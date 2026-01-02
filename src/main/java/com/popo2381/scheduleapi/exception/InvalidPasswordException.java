package com.popo2381.scheduleapi.exception;

/**
 * 비밀번호가 일치하지 않을 때 발생하는 예외
 */
public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException() {
        super("잘못된 비밀번호입니다.");
    }
}