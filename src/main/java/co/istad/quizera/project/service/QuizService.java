package co.istad.quizera.project.service;

import co.istad.quizera.project.dto.quiz.QuizCreateRequest;
import co.istad.quizera.project.dto.quiz.QuizResponse;

import java.util.List;

public interface QuizService {

    QuizResponse createQuiz(Long teacherId, QuizCreateRequest request);

    QuizResponse getQuizById(Long id);

    List<QuizResponse> getAllPublicQuizzes();

    List<QuizResponse> getAllQuizzes();

    List<QuizResponse> getMyQuizzes(Long teacherId);

    QuizResponse updateQuiz(Long id, QuizCreateRequest request);

    void deleteQuiz(Long id);
}