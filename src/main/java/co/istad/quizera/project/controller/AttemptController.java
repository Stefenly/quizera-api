package co.istad.quizera.project.controller;

import co.istad.quizera.project.dto.quiz.*;
import co.istad.quizera.project.service.QuizAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attempts")
@RequiredArgsConstructor
public class AttemptController {

    private final QuizAttemptService quizAttemptService;

    // SUBMIT QUIZ (AUTO GRADING)
    @PostMapping("/submit")
    public QuizAttemptResultDto submit(@RequestBody QuizSubmitRequest request) {
        return quizAttemptService.submitAttempt(request);
    }

    // GET ALL
    @GetMapping
    public List<QuizAttemptDto> getAll() {
        return quizAttemptService.getAllAttempts();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public QuizAttemptDto getById(@PathVariable Long id) {
        return quizAttemptService.getAttemptById(id);
    }


    // GET BY USER
    @GetMapping("/user/{userId}")
    public List<QuizAttemptDto> getByUser(@PathVariable Long userId) {
        return quizAttemptService.getAttemptsByUser(userId);
    }

    // GET BY QUIZ
    @GetMapping("/quiz/{quizId}")
    public List<QuizAttemptDto> getByQuiz(@PathVariable Long quizId) {
        return quizAttemptService.getAttemptsByQuiz(quizId);
    }

    // UPDATE
    @PutMapping("/{id}")
    public QuizAttemptDto update(@PathVariable Long id,
                                 @RequestBody QuizAttemptDto dto) {
        return quizAttemptService.updateAttempt(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        quizAttemptService.deleteAttempt(id);
        return "Deleted successfully";
    }
}