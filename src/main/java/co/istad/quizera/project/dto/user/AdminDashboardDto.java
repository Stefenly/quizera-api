package co.istad.quizera.project.dto.user;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
public class AdminDashboardDto {

    private long totalUsers;
    private long totalQuizzes;
    private long totalAttempts;
    private long totalFlashcards;

    private List<LeaderboardDto> topUsers;

    private double averageScore;
}
