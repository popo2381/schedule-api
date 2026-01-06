package com.popo2381.scheduleapi.repository;

import com.popo2381.scheduleapi.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByWriter(String name);
}
