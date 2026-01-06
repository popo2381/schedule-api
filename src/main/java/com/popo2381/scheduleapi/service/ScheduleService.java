package com.popo2381.scheduleapi.service;

import com.popo2381.scheduleapi.dto.*;
import com.popo2381.scheduleapi.entity.Schedule;
import com.popo2381.scheduleapi.exception.InvalidPasswordException;
import com.popo2381.scheduleapi.exception.ScheduleNotFoundException;
import com.popo2381.scheduleapi.repository.CommentRepository;
import com.popo2381.scheduleapi.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final CommentService commentService;
    private final CommentRepository commentRepository;

    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                request.getWriter(),
                request.getPassword()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getWriter(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public GetScheduleResponse findOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException(scheduleId)
        );
        List<CommentResponse> comments = commentRepository.findAllByScheduleId(scheduleId).stream().
                map(comment -> new CommentResponse(
                        comment.getId(),
                        comment.getContent(),
                        comment.getWriter(),
                        comment.getCreatedAt(),
                        comment.getModifiedAt()
                )).toList();

        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getWriter(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt(),
                comments
        );
    }

    @Transactional(readOnly = true)
    public List<GetAllScheduleResponse> findAllByWriter(String writer) {
        if (writer == null || writer.isBlank()) {
            List<Schedule> schedules = scheduleRepository.findAll();
            return schedules.stream()
                    .map(
                            schedule -> new GetAllScheduleResponse(
                                    schedule.getId(),
                                    schedule.getTitle(),
                                    schedule.getContent(),
                                    schedule.getWriter(),
                                    schedule.getCreatedAt(),
                                    schedule.getModifiedAt()
                            )
                    ).toList();
        }
        List<Schedule> schedules = scheduleRepository.findByWriter(writer);
        List<GetAllScheduleResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            GetAllScheduleResponse dto = new GetAllScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getWriter(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
            dtos.add(dto);
        };
        return dtos;
    }

    @Transactional
    public UpdateScheduleResponse updateTitleAndName(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException(scheduleId)
        );
        if (!request.getPassword().equals(schedule.getPassword())) {
            throw new InvalidPasswordException();
        }
        schedule.updateTitleAndName(request.getTitle(), request.getWriter());
        scheduleRepository.flush();
        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getWriter(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public Void delete(Long scheduleId, DeleteScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException(scheduleId)
        );
        if (!request.getPassword().equals(schedule.getPassword())) {
            throw new InvalidPasswordException();
        }
        scheduleRepository.deleteById(scheduleId);
        return null;
    }
}
