package com.examination.dto;

import com.examination.model.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private Long id;
    private Long examId;
    private String questionText;
    private Question.QuestionType questionType;
    private Integer marks;
    private Integer orderNumber;
    private Boolean mandatory;
    private String explanation;
}
