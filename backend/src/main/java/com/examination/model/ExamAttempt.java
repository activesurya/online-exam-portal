package com.examination.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "exam_attempts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

    @Column(nullable = false)
    private Long totalTimeSpentSeconds = 0L;

    @Column(nullable = false)
    private Integer totalMarksObtained = 0;

    @Column(nullable = false)
    private Integer totalQuestions;

    @Column(nullable = false)
    private Integer answeredQuestions = 0;

    @Column(nullable = false)
    private Integer correctAnswers = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttemptStatus status = AttemptStatus.IN_PROGRESS;

    @Column
    private Double percentage = 0.0;

    @Column
    private String grade;

    @OneToMany(mappedBy = "examAttempt", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Answer> answers = new HashSet<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void calculatePercentage() {
        if (exam.getTotalMarks() > 0) {
            this.percentage = (double) (this.totalMarksObtained * 100) / exam.getTotalMarks();
        }
    }

    public enum AttemptStatus {
        IN_PROGRESS,
        SUBMITTED,
        GRADED,
        CANCELLED
    }
}
