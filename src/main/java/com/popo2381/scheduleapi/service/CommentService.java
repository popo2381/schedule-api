package com.popo2381.scheduleapi.service;

import com.popo2381.scheduleapi.dto.CommentRequest;
import com.popo2381.scheduleapi.dto.CommentResponse;
import com.popo2381.scheduleapi.dto.UpdateScheduleResponse;
import com.popo2381.scheduleapi.entity.Comment;
import com.popo2381.scheduleapi.entity.Schedule;
import com.popo2381.scheduleapi.exception.CommentLimitExceededException;
import com.popo2381.scheduleapi.exception.CommentNotFoundException;
import com.popo2381.scheduleapi.exception.InvalidPasswordException;
import com.popo2381.scheduleapi.exception.ScheduleNotFoundException;
import com.popo2381.scheduleapi.repository.CommentRepository;
import com.popo2381.scheduleapi.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CommentResponse save(Long scheduleId, CommentRequest request) {
        // 해당 스케줄 존재 여부 확인
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException(scheduleId)
        );
        // 해당 스케줄id를 10개 가지고 있는 댓글 체크
        if(commentRepository.countByScheduleId(scheduleId) >= 10) {
            throw new CommentLimitExceededException();
        }
        // comment가 FK를 가지므로 생성 시에 schedule 객체를 넣어서 관계를 이어줌
        Comment comment = new Comment(
                request.getWriter(),
                request.getContent(),
                request.getPassword(),
                schedule
        );

        Comment saved = commentRepository.save(comment);
        return new CommentResponse(
                saved.getId(),
                saved.getContent(),
                saved.getWriter(),
                saved.getCreatedAt(),
                saved.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> findAllByScheduleId(Long scheduleId) {
        return commentRepository.findAllByScheduleId(scheduleId).stream().
                map(comment -> new CommentResponse(
                        comment.getId(),
                        comment.getContent(),
                        comment.getWriter(),
                        comment.getCreatedAt(),
                        comment.getModifiedAt()
                )).toList();
    }

    public CommentResponse update(Long scheduleId, Long commentId, CommentRequest request) {
        // 일정이 존재하는지 확인
        scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException(scheduleId)
        );
        // 댓글이 존재하는지 확인
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new CommentNotFoundException(commentId)
        );
        // 해당 일정의 댓글이 맞는지 검증
        if (!comment.getSchedule().getId().equals(scheduleId)) {
            throw new CommentNotFoundException(commentId);
        };
        // 비밀번호 검증
        if (!request.getPassword().equals(comment.getPassword())) {
            throw new InvalidPasswordException();
        }
        comment.update(request.getWriter(),request.getContent());
        scheduleRepository.flush();
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getWriter(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }
}
