package co.istad.quizera.project.controller;

import co.istad.quizera.project.dto.user.LeaderboardDto;
import co.istad.quizera.project.service.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
@RequiredArgsConstructor
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    @GetMapping
    public List<LeaderboardDto> getLeaderboard(
            @RequestParam(defaultValue = "10") int limit
    ) {
        return leaderboardService.getTopUsers(limit);
    }
}
