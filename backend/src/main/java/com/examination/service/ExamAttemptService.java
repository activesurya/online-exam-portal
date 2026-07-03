package com.examination.service;

import com.examination.dto.ExamAttemptDTO;
import com.examination.model.ExamAttempt;
import com.examination.model.Exam;
import com.examination.model.User;
import com.examination.repository.ExamAttemptRepository;
import com.examination.repository.ExamRepository;
import com.examination.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExamAttemptService {

    @Autowired
    private ExamAttemptRepository examAttemptRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ExamAttemptDTO startExam(Long examId, Long studentId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        ExamAttempt attempt = new ExamAttempt();
        attempt.setExam(exam);
        attempt.setStudent(student);
        attempt.setStartTime(LocalDateTime.now());
        attempt.setStatus(ExamAttempt.AttemptStatus.IN_PROGRESS);
        attempt.setTotalQuestions(exam.getTotalQuestions());

        ExamAttempt savedAttempt = examAttemptRepository.save(attempt);
        return modelMapper.map(savedAttempt, ExamAttemptDTO.class);
    }

    public ExamAttemptDTO submitExam(Long attemptId) {
        ExamAttempt attempt = examAttemptRepository.findById(attemptId)
                .orElseThrow(() -> new RuntimeException("Exam attempt not found"));
        attempt.setEndTime(LocalDateTime.now());
        attempt.setStatus(ExamAttempt.AttemptStatus.SUBMITTED);
        attempt.calculatePercentage();
        ExamAttempt savedAttempt = examAttemptRepository.save(attempt);
        return modelMapper.map(savedAttempt, ExamAttemptDTO.class);
    }

    public ExamAttemptDTO getAttemptById(Long id) {
        ExamAttempt attempt = examAttemptRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam attempt not found"));
        return modelMapper.map(attempt, ExamAttemptDTO.class);
    }

    public List<ExamAttemptDTO> getAttemptsByStudent(Long studentId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return examAttemptRepository.findByStudent(student).stream()
                .map(a -> modelMapper.map(a, ExamAttemptDTO.class))
                .collect(Collectors.toList());
    }

    public List<ExamAttemptDTO> getAttemptsByExam(Long examId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
        return examAttemptRepository.findByExam(exam).stream()
                .map(a -> modelMapper.map(a, ExamAttemptDTO.class))
                .collect(Collectors.toList());
    }
}
