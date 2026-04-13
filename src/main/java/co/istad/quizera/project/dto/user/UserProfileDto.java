package co.istad.quizera.project.dto.user;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserProfileDto {
    private Long id;
    private String name;
    private String email;
    private List<String> roles;
}