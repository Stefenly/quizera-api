package co.istad.quizera.project.service;

import co.istad.quizera.project.dto.user.LeaderboardDto;

import java.util.List;

public interface LeaderboardService {

    List<LeaderboardDto> getTopUsers(int limit);
}
