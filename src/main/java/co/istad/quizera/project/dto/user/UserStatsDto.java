package co.istad.quizera.project.dto.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserStatsDto {
    private int totalQuizzesTaken;
    private int totalPoints;
    private int currentLevel;
}