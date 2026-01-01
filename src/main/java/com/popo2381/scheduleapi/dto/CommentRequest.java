package com.popo2381.scheduleapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
/**
 * 댓글 생성/수정 요청 DTO
 */
@Getter
public class CommentRequest {
    @NotBlank(message = "내용을 입력해주세요.")
    @Size(max = 100, message = "댓글은 최대 100자 이내로 입력해주세요")
    private String content;

    @NotBlank(message = "작성자명을 입력해주세요.")
    private String writer;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
