package com.examination.repository;

import com.examination.model.Answer;
import com.examination.model.ExamAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByExamAttempt(ExamAttempt examAttempt);
}
