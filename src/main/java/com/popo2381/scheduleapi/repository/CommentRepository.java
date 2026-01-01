package com.popo2381.scheduleapi.repository;

import com.popo2381.scheduleapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    long countByScheduleId(Long scheduleId);
    List<Comment> findAllByScheduleId(Long scheduleId);
}
