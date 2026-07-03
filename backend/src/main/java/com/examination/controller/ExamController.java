package com.examination.controller;

import com.examination.dto.ExamDTO;
import com.examination.service.ExamService;
import com.examination.model.User;
import com.examination.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/exams")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @PreAuthorize("hasRole('STAFF') or hasRole('ADMIN')")
    public ResponseEntity<ExamDTO> createExam(@RequestBody ExamDTO examDTO, Authentication authentication) {
        User createdBy = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        ExamDTO createdExam = examService.createExam(examDTO, createdBy);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExam);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamDTO> getExamById(@PathVariable Long id) {
        ExamDTO exam = examService.getExamById(id);
        return ResponseEntity.ok(exam);
    }

    @GetMapping
    public ResponseEntity<List<ExamDTO>> getAllExams() {
        List<ExamDTO> exams = examService.getAllExams();
        return ResponseEntity.ok(exams);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('STAFF') or hasRole('ADMIN')")
    public ResponseEntity<ExamDTO> updateExam(@PathVariable Long id, @RequestBody ExamDTO examDTO) {
        ExamDTO updatedExam = examService.updateExam(id, examDTO);
        return ResponseEntity.ok(updatedExam);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }
}
