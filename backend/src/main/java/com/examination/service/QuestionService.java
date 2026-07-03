package com.examination.service;

import com.examination.dto.QuestionDTO;
import com.examination.model.Question;
import com.examination.model.Exam;
import com.examination.repository.QuestionRepository;
import com.examination.repository.ExamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ModelMapper modelMapper;

    public QuestionDTO createQuestion(Long examId, QuestionDTO questionDTO) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
        Question question = modelMapper.map(questionDTO, Question.class);
        question.setExam(exam);
        Question savedQuestion = questionRepository.save(question);
        return modelMapper.map(savedQuestion, QuestionDTO.class);
    }

    public QuestionDTO getQuestionById(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        return modelMapper.map(question, QuestionDTO.class);
    }

    public List<QuestionDTO> getQuestionsByExamId(Long examId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
        return questionRepository.findByExamOrderByOrderNumber(exam).stream()
                .map(q -> modelMapper.map(q, QuestionDTO.class))
                .collect(Collectors.toList());
    }

    public QuestionDTO updateQuestion(Long id, QuestionDTO questionDTO) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        modelMapper.map(questionDTO, question);
        Question updatedQuestion = questionRepository.save(question);
        return modelMapper.map(updatedQuestion, QuestionDTO.class);
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
