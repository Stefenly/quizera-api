package co.istad.quizera.project.controller;

import co.istad.quizera.project.dto.user.UserProfileDto;
import co.istad.quizera.project.dto.user.UserUpdateRequest;
import co.istad.quizera.project.entity.Role;
import co.istad.quizera.project.entity.User;
import co.istad.quizera.project.enums.UserRole;
import co.istad.quizera.project.repository.RoleRepository;
import co.istad.quizera.project.repository.UserRepository;
import co.istad.quizera.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    // GET ALL USERS (ADMIN ONLY)
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    @GetMapping
    public ResponseEntity<List<UserProfileDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // GET USER BY ID (ADMIN ONLY)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // GET CURRENT USER
    @GetMapping("/me")
    public ResponseEntity<UserProfileDto> getMe(Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    // UPDATE USER (SELF OR ADMIN)
    @PutMapping("/{id}")
    public ResponseEntity<UserProfileDto> updateUser(
            @PathVariable Long id,
            @RequestBody UserUpdateRequest request
    ) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    // DELETE USER (ADMIN ONLY)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    // MAKE ADMIN (FIXED)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/make-admin/{id}")
    public ResponseEntity<String> makeAdmin(@PathVariable Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Role adminRole = roleRepository.findByName(UserRole.ADMIN)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.getRoles().clear();
        user.getRoles().add(adminRole);

        userRepository.save(user);

        return ResponseEntity.ok("User is now ADMIN");
    }

    // BECOME TEACHER
    @PutMapping("/become-teacher")
    public ResponseEntity<String> becomeTeacher(Authentication auth) {

        User user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Role teacherRole = roleRepository.findByName(UserRole.TEACHER)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.getRoles().add(teacherRole);

        userRepository.save(user);

        return ResponseEntity.ok("User is now TEACHER");
    }
}