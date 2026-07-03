package com.examination.repository;

import com.examination.model.Question;
import com.examination.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByExam(Exam exam);
    
    List<Question> findByExamOrderByOrderNumber(Exam exam);
    
    long countByExam(Exam exam);
}
