package com.examination.service;

import com.examination.dto.ExamDTO;
import com.examination.model.Exam;
import com.examination.model.User;
import com.examination.repository.ExamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ExamDTO createExam(ExamDTO examDTO, User createdBy) {
        Exam exam = modelMapper.map(examDTO, Exam.class);
        exam.setCreatedBy(createdBy);
        exam.setStatus(Exam.ExamStatus.DRAFT);
        Exam savedExam = examRepository.save(exam);
        return modelMapper.map(savedExam, ExamDTO.class);
    }

    public ExamDTO getExamById(Long id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
        return modelMapper.map(exam, ExamDTO.class);
    }

    public List<ExamDTO> getAllExams() {
        return examRepository.findAll().stream()
                .map(exam -> modelMapper.map(exam, ExamDTO.class))
                .collect(Collectors.toList());
    }

    public List<ExamDTO> getExamsByCreator(User createdBy) {
        return examRepository.findByCreatedBy(createdBy).stream()
                .map(exam -> modelMapper.map(exam, ExamDTO.class))
                .collect(Collectors.toList());
    }

    public ExamDTO updateExam(Long id, ExamDTO examDTO) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
        modelMapper.map(examDTO, exam);
        Exam updatedExam = examRepository.save(exam);
        return modelMapper.map(updatedExam, ExamDTO.class);
    }

    public void deleteExam(Long id) {
        examRepository.deleteById(id);
    }

    public List<ExamDTO> getOngoingExams() {
        LocalDateTime now = LocalDateTime.now();
        return examRepository.findByStatusAndStartTimeBeforeAndEndTimeAfter(
                Exam.ExamStatus.ONGOING, now, now).stream()
                .map(exam -> modelMapper.map(exam, ExamDTO.class))
                .collect(Collectors.toList());
    }
}
