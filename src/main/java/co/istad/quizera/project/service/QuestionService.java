package co.istad.quizera.project.service;


import co.istad.quizera.project.dto.quiz.QuestionDto;

import java.util.List;

public interface QuestionService {
    QuestionDto createQuestion(Long quizId, QuestionDto dto);
    List<QuestionDto> getQuestionsByQuiz(Long quizId);
    List<QuestionDto> createQuestions(Long quizId, List<QuestionDto> dtos);
    QuestionDto getQuestionById(Long id);
    QuestionDto updateQuestion(Long id, QuestionDto dto);
    void deleteQuestion(Long id);
}