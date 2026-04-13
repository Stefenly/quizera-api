package co.istad.quizera.project.service.impl;

import co.istad.quizera.project.dto.quiz.GamificationResultDto;
import co.istad.quizera.project.service.GamificationService;
import org.springframework.stereotype.Service;

@Service
public class GamificationServiceImpl implements GamificationService {

    @Override
    public GamificationResultDto calculate(int score, int totalQuestions, int streakCount, int currentXP) {

        int xpEarned = score * 10;

        int streakBonus = calculateStreakBonus(streakCount);

        int totalXP = currentXP + xpEarned + streakBonus;

        int level = totalXP / 100;

        String badge = determineBadge(score, totalQuestions);

        return GamificationResultDto.builder()
                .xpEarned(xpEarned)
                .totalXP(totalXP)
                .level(level)
                .streakBonus(streakBonus)
                .badge(badge)
                .build();
    }

    private int calculateStreakBonus(int streakCount) {
        if (streakCount >= 5) return 50;
        if (streakCount >= 3) return 20;
        return 0;
    }

    private String determineBadge(int score, int totalQuestions) {
        if (score == totalQuestions) return "PERFECT_SCORE";
        if (score >= totalQuestions * 0.8) return "EXCELLENT";
        return "PARTICIPANT";
    }
}