package co.istad.quizera.project.service;

import co.istad.quizera.project.dto.user.UserProfileDto;
import co.istad.quizera.project.dto.user.UserUpdateRequest;

import java.util.List;

public interface UserService {

    List<UserProfileDto> getAllUsers();

    UserProfileDto getUserById(Long id);

    UserProfileDto getUserByEmail(String email);

    UserProfileDto updateUser(Long id, UserUpdateRequest request);

    void deleteUser(Long id);
}