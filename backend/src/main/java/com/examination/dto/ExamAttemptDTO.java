package com.examination.dto;

import com.examination.model.ExamAttempt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamAttemptDTO {
    private Long id;
    private Long examId;
    private Long studentId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long totalTimeSpentSeconds;
    private Integer totalMarksObtained;
    private Integer totalQuestions;
    private Integer answeredQuestions;
    private Integer correctAnswers;
    private ExamAttempt.AttemptStatus status;
    private Double percentage;
    private String grade;
}
