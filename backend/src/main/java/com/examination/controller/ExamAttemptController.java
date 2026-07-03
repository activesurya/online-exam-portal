package com.examination.controller;

import com.examination.dto.ExamAttemptDTO;
import com.examination.service.ExamAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/exam-attempts")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExamAttemptController {

    @Autowired
    private ExamAttemptService examAttemptService;

    @PostMapping("/start/{examId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ExamAttemptDTO> startExam(@PathVariable Long examId, 
                                                    @RequestParam Long studentId) {
        ExamAttemptDTO attempt = examAttemptService.startExam(examId, studentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(attempt);
    }

    @PutMapping("/{attemptId}/submit")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ExamAttemptDTO> submitExam(@PathVariable Long attemptId) {
        ExamAttemptDTO attempt = examAttemptService.submitExam(attemptId);
        return ResponseEntity.ok(attempt);
    }

    @GetMapping("/{attemptId}")
    public ResponseEntity<ExamAttemptDTO> getAttemptById(@PathVariable Long attemptId) {
        ExamAttemptDTO attempt = examAttemptService.getAttemptById(attemptId);
        return ResponseEntity.ok(attempt);
    }

    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasRole('STUDENT') or hasRole('ADMIN')")
    public ResponseEntity<List<ExamAttemptDTO>> getStudentAttempts(@PathVariable Long studentId) {
        List<ExamAttemptDTO> attempts = examAttemptService.getAttemptsByStudent(studentId);
        return ResponseEntity.ok(attempts);
    }

    @GetMapping("/exam/{examId}")
    @PreAuthorize("hasRole('STAFF') or hasRole('ADMIN')")
    public ResponseEntity<List<ExamAttemptDTO>> getExamAttempts(@PathVariable Long examId) {
        List<ExamAttemptDTO> attempts = examAttemptService.getAttemptsByExam(examId);
        return ResponseEntity.ok(attempts);
    }
}
