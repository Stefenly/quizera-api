package co.istad.quizera.project.service.impl;

import co.istad.quizera.project.dto.auth.AuthResponse;
import co.istad.quizera.project.dto.auth.LoginRequest;
import co.istad.quizera.project.dto.auth.RegisterRequest;
import co.istad.quizera.project.entity.Role;
import co.istad.quizera.project.entity.User;
import co.istad.quizera.project.enums.UserRole;
import co.istad.quizera.project.exception.BadRequestException;
import co.istad.quizera.project.repository.RoleRepository;
import co.istad.quizera.project.repository.UserRepository;
import co.istad.quizera.project.service.AuthService;
import co.istad.quizera.project.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        // DEFAULT ROLE = STUDENT
        UserRole roleType = (request.getRole() == null)
                ? UserRole.ROLE_STUDENT
                : request.getRole();

        // BLOCK ADMIN FROM FRONTEND (IMPORTANT SECURITY RULE)
        if (roleType == UserRole.ROLE_ADMIN) {
            throw new BadRequestException("Cannot register as ADMIN");
        }

        Role role = roleRepository.findByName(roleType)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(role))
                .build();

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail());

        return AuthResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        return AuthResponse.builder()
                .accessToken(jwtUtil.generateToken(request.getEmail()))
                .build();
    }
}