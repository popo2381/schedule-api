package com.popo2381.scheduleapi.exception;

/**
 * 일정이 존재하지 않을 때 발생하는 예외
 */
public class    ScheduleNotFoundException extends RuntimeException {

    public ScheduleNotFoundException(Long scheduleId) {
        super("해당 일정을 찾을 수 없습니다. id=" + scheduleId);
    }
}