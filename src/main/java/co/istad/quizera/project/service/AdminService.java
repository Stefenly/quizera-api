package co.istad.quizera.project.service;

import co.istad.quizera.project.dto.quiz.QuizResponse;
import co.istad.quizera.project.dto.user.AdminDashboardDto;
import co.istad.quizera.project.dto.user.LeaderboardDto;
import co.istad.quizera.project.dto.user.UserProfileDto;

import java.util.List;

public interface AdminService {

    AdminDashboardDto getDashboard();

    List<UserProfileDto> getAllUsers();

    void deleteUser(Long id);

    List<QuizResponse> getAllQuizzes();

    void deleteQuiz(Long id);

    List<LeaderboardDto> getLeaderboard(int limit);
}