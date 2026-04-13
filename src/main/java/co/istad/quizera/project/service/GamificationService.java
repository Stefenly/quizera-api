package co.istad.quizera.project.service;

import co.istad.quizera.project.dto.quiz.GamificationResultDto;

public interface GamificationService {

    GamificationResultDto calculate(int score, int totalQuestions, int streakCount, int currentXP);
}