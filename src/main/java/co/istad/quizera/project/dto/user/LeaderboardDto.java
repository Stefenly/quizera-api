package co.istad.quizera.project.dto.user;

import lombok.*;

@Getter
@Setter
@Builder
public class LeaderboardDto {

    private Long userId;
    private String name;
    private Integer totalXP;
    private Integer rank;
}
