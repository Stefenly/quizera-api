package co.istad.quizera.project.service;

import co.istad.quizera.project.dto.quiz.QuizCreateRequest;
import co.istad.quizera.project.dto.quiz.QuizResponse;

import java.util.List;

public interface QuizService {

    // CREATE
    QuizResponse createQuiz(QuizCreateRequest request);

    // READ
    QuizResponse getQuizById(Long id);

    List<QuizResponse> getAllPublicQuizzes();

    List<QuizResponse> getAllQuizzes(); // admin only

    List<QuizResponse> getMyQuizzes(); // current logged-in user

    // UPDATE
    QuizResponse updateQuiz(Long id, QuizCreateRequest request);

    // DELETE
    void deleteQuiz(Long id);
}