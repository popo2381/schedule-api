package com.popo2381.scheduleapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

/**
 * 전역 예외 처리기
 *
 * - Validation 실패
 * - 리소스 없음 (404)
 * - 비밀번호 불일치 (403)
 * - 비즈니스 규칙 위반 (409)
 * - 기타 서버 오류 (500)
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /* ===========================
       400 Bad Request - Validation
       =========================== */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(
            MethodArgumentNotValidException e) {

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );

        Map<String, Object> response = new HashMap<>();
        response.put("code", "VALIDATION_FAILED");
        response.put("message", "입력값 검증에 실패했습니다.");
        response.put("errors", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /* ===========================
       400 Bad Request - 타입 오류
       =========================== */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleTypeMismatchException() {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                error("INVALID_PARAMETER", "요청 파라미터 형식이 올바르지 않습니다.")
        );
    }

    /* ===========================
       404 Not Found
       =========================== */
    @ExceptionHandler({
            ScheduleNotFoundException.class,
            CommentNotFoundException.class
    })
    public ResponseEntity<Map<String, Object>> handleNotFoundException(RuntimeException e) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                error("NOT_FOUND", e.getMessage())
        );
    }

    /* ===========================
       403 Forbidden
       =========================== */
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidPasswordException() {

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                error("INVALID_PASSWORD", "비밀번호가 일치하지 않습니다.")
        );
    }

    /* ===========================
       409 Conflict
       =========================== */
    @ExceptionHandler(CommentLimitExceededException.class)
    public ResponseEntity<Map<String, Object>> handleCommentLimitException() {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                error("COMMENT_LIMIT_EXCEEDED", "댓글은 최대 10개까지만 작성할 수 있습니다.")
        );
    }

    /* ===========================
       500 Internal Server Error
       =========================== */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                error("INTERNAL_SERVER_ERROR", "서버 내부 오류가 발생했습니다.")
        );
    }

    /* ===========================
       공통 에러 응답 생성 메서드
       =========================== */
    private Map<String, Object> error(String code, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("message", message);
        return map;
    }
}
