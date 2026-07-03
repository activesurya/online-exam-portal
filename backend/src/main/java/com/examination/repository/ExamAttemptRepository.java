package com.examination.repository;

import com.examination.model.ExamAttempt;
import com.examination.model.Exam;
import com.examination.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExamAttemptRepository extends JpaRepository<ExamAttempt, Long> {
    List<ExamAttempt> findByStudent(User student);
    
    List<ExamAttempt> findByExam(Exam exam);
    
    List<ExamAttempt> findByExamAndStudent(Exam exam, User student);
    
    Optional<ExamAttempt> findByExamAndStudentAndStatus(
        Exam exam,
        User student,
        ExamAttempt.AttemptStatus status
    );
    
    long countByExamAndStatus(Exam exam, ExamAttempt.AttemptStatus status);
}
