package com.popo2381.scheduleapi.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String writer;

    @Column(length = 100)
    private String content;

    @Column(length = 20, nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    public Comment(String writer, String content, String password, Schedule schedule) {
        this.writer = writer;
        this.content = content;
        this.password = password;
        this.schedule = schedule;
    }

    public void update(String writer, String content) {
        this.writer = writer;
        this.content = content;
    }
}
