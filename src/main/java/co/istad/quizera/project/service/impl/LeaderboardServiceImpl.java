package co.istad.quizera.project.service.impl;

import co.istad.quizera.project.dto.user.LeaderboardDto;
import co.istad.quizera.project.entity.User;
import co.istad.quizera.project.repository.UserRepository;
import co.istad.quizera.project.service.LeaderboardService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaderboardServiceImpl implements LeaderboardService {

    private final UserRepository userRepository;

    @Override
    public List<LeaderboardDto> getTopUsers(int limit) {

        Pageable pageable = PageRequest.of(0, limit);

        List<User> users = userRepository
                .findAllByOrderByTotalXPDesc(pageable)
                .getContent();

        List<LeaderboardDto> leaderboard = new ArrayList<>();

        int rank = 1;

        for (User user : users) {

            leaderboard.add(LeaderboardDto.builder()
                    .userId(user.getId())
                    .name(user.getEmail())
                    .totalXP(user.getTotalXP())
                    .rank(rank++)
                    .build());
        }

        return leaderboard;
    }
}
