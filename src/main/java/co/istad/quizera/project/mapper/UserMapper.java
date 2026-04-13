package co.istad.quizera.project.mapper;

import co.istad.quizera.project.dto.user.UserProfileDto;
import co.istad.quizera.project.dto.user.UserUpdateRequest;
import co.istad.quizera.project.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserProfileDto toProfileDto(User user) {
        return UserProfileDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .roles(
                        user.getRoles()
                                .stream()
                                .map(r -> r.getName().name())
                                .toList()
                )
                .build();
    }

    public void updateEntity(User user, UserUpdateRequest request) {
        if (request.getName() != null) {
            user.setName(request.getName());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
    }
}