package com.examination.repository;

import com.examination.model.Exam;
import com.examination.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findByCreatedBy(User createdBy);
    
    List<Exam> findByStatus(Exam.ExamStatus status);
    
    List<Exam> findByStatusAndStartTimeBeforeAndEndTimeAfter(
        Exam.ExamStatus status,
        LocalDateTime endTime,
        LocalDateTime startTime
    );
}
