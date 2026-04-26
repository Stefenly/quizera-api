package co.istad.quizera.project.service.impl;

import co.istad.quizera.project.dto.quiz.QuizResponse;
import co.istad.quizera.project.dto.user.AdminDashboardDto;
import co.istad.quizera.project.dto.user.LeaderboardDto;
import co.istad.quizera.project.dto.user.UserProfileDto;
import co.istad.quizera.project.entity.QuizAttempt;
import co.istad.quizera.project.repository.*;
import co.istad.quizera.project.service.AdminService;
import co.istad.quizera.project.service.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final QuizRepository quizRepository;
    private final QuizAttemptRepository attemptRepository;
    private final FlashcardRepository flashcardRepository;
    private final LeaderboardService leaderboardService;

    // DASHBOARD
    @Override
    public AdminDashboardDto getDashboard() {

        double avgScore = attemptRepository.findAll()
                .stream()
                .filter(a -> a.getScore() != null)
                .mapToInt(QuizAttempt::getScore)
                .average()
                .orElse(0.0);

        return AdminDashboardDto.builder()
                .totalUsers(userRepository.count())
                .totalQuizzes(quizRepository.count())
                .totalAttempts(attemptRepository.count())
                .totalFlashcards(flashcardRepository.count())
                .topUsers(leaderboardService.getTopUsers(10))
                .averageScore(avgScore)
                .build();
    }

    // USERS
    @Override
    public List<UserProfileDto> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(user -> UserProfileDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .roles(
                                user.getRoles()
                                        .stream()
                                        .map(role -> role.getName().name())
                                        .toList()
                        )
                        .build()
                )
                .toList();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // QUIZZES (FULL SAFE FIX)
    @Override
    public List<QuizResponse> getAllQuizzes() {

        return quizRepository.findAll()
                .stream()
                .map(q -> QuizResponse.builder()
                        .id(q.getId())
                        .title(q.getTitle())
                        .classroomId(q.getClassroom() != null ? q.getClassroom().getId() : null)
                        .createdById(q.getCreatedBy() != null ? q.getCreatedBy().getId() : null)
                        .isPublic(q.getIsPublic())
                        .createdAt(q.getCreatedAt())

                        .totalQuestions(
                                q.getQuestions() != null ? q.getQuestions().size() : 0
                        )

                        .build()
                )
                .toList();
    }

    @Override
    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }

    // LEADERBOARD
    @Override
    public List<LeaderboardDto> getLeaderboard(int limit) {
        return leaderboardService.getTopUsers(limit);
    }
}