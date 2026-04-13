package co.istad.quizera.project.service;

import co.istad.quizera.project.dto.quiz.*;

import java.util.List;

public interface QuizAttemptService {

    QuizAttemptResultDto submitAttempt(QuizSubmitRequest request);

    List<QuizAttemptDto> getAllAttempts();

    QuizAttemptDto getAttemptById(Long id);

    List<QuizAttemptDto> getAttemptsByUser(Long userId);

    List<QuizAttemptDto> getAttemptsByQuiz(Long quizId);

    QuizAttemptDto updateAttempt(Long id, QuizAttemptDto dto);

    void deleteAttempt(Long id);
}