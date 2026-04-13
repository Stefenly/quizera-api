package co.istad.quizera.project.dto.quiz;

import lombok.*;

@Getter
@Setter
@Builder
public class GamificationResultDto {

    private int xpEarned;
    private int totalXP;
    private int level;
    private int streakBonus;
    private String badge;

}
