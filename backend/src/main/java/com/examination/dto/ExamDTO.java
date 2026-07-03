package com.examination.dto;

import com.examination.model.Exam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamDTO {
    private Long id;
    private String title;
    private String description;
    private Integer totalQuestions;
    private Integer totalMarks;
    private Integer durationMinutes;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Exam.ExamStatus status;
    private Boolean negativeMarking;
    private Double negativeMarkPercentage;
    private Boolean shuffleQuestions;
    private Boolean shuffleOptions;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
