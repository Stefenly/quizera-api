package co.istad.quizera.project.controller;

import co.istad.quizera.project.dto.quiz.QuizResponse;
import co.istad.quizera.project.dto.user.AdminDashboardDto;
import co.istad.quizera.project.dto.user.LeaderboardDto;
import co.istad.quizera.project.dto.user.UserProfileDto;
import co.istad.quizera.project.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/dashboard")
    public AdminDashboardDto getDashboard() {
        return adminService.getDashboard();
    }

    @GetMapping("/users")
    public List<UserProfileDto> getAllUsers() {
        return adminService.getAllUsers();
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return "User deleted";
    }

    @GetMapping("/quizzes")
    public List<QuizResponse> getAllQuizzes() {
        return adminService.getAllQuizzes();
    }

    @DeleteMapping("/quizzes/{id}")
    public String deleteQuiz(@PathVariable Long id) {
        adminService.deleteQuiz(id);
        return "Quiz deleted";
    }

    @GetMapping("/stats/leaderboard")
    public List<LeaderboardDto> getLeaderboard() {
        return adminService.getLeaderboard(10);
    }
}