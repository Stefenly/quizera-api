package co.istad.quizera.project.service.impl;

import co.istad.quizera.project.dto.user.UserProfileDto;
import co.istad.quizera.project.dto.user.UserUpdateRequest;
import co.istad.quizera.project.entity.User;
import co.istad.quizera.project.mapper.UserMapper;
import co.istad.quizera.project.repository.UserRepository;
import co.istad.quizera.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    // GET ALL USERS
    @Override
    public List<UserProfileDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toProfileDto)
                .collect(Collectors.toList());
    }

    // GET USER BY ID
    @Override
    public UserProfileDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return userMapper.toProfileDto(user);
    }

    // GET USER BY EMAIL
    @Override
    public UserProfileDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return userMapper.toProfileDto(user);
    }

    // UPDATE USER
    @Override
    public UserProfileDto updateUser(Long id, UserUpdateRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userMapper.updateEntity(user, request);

        userRepository.save(user);

        return userMapper.toProfileDto(user);
    }

    // DELETE USER
    @Override
    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);
    }
}