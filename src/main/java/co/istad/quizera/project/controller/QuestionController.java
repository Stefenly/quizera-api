package co.istad.quizera.project.controller;

import co.istad.quizera.project.dto.quiz.QuestionDto;
import co.istad.quizera.project.entity.Question;
import co.istad.quizera.project.service.FileUploadService;
import co.istad.quizera.project.service.QuestionService;
import co.istad.quizera.project.util.FileValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final FileUploadService fileUploadService;

    // CREATE
//    @PostMapping("/{quizId}")
//    public List<QuestionDto> createQuestions(
//            @PathVariable Long quizId,
//            @RequestBody List<QuestionDto> dtos
//    ) {
//        return questionService.createQuestions(quizId, dtos);
//    }

//    //CREATE MULTIPLE QUESTIONS
//    @PostMapping("/{quizId}")
//    public List<QuestionDto> createQuestions(
//            @PathVariable Long quizId,
//            @RequestBody List<QuestionDto> dtos
//    ) {
//        return questionService.createQuestions(quizId, dtos);
//    }
//
//    @PostMapping("/{quizId}")
//    public QuestionDto create(@PathVariable Long quizId,
//                              @RequestBody QuestionDto dto) {
//        return questionService.createQuestion(quizId, dto);
//    }

    // CREATE MULTIPLE QUESTIONS
    @PostMapping("/{quizId}/bulk")
    public List<QuestionDto> createQuestions(
            @PathVariable Long quizId,
            @RequestBody List<QuestionDto> dtos
    ) {
        return questionService.createQuestions(quizId, dtos);
    }

    // CREATE SINGLE QUESTION
    @PostMapping("/{quizId}")
    public QuestionDto create(
            @PathVariable Long quizId,
            @RequestBody QuestionDto dto
    ) {
        return questionService.createQuestion(quizId, dto);
    }

    // GET BY QUIZ
    @GetMapping("/quiz/{quizId}")
    public List<QuestionDto> getByQuiz(@PathVariable Long quizId) {
        return questionService.getQuestionsByQuiz(quizId);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public QuestionDto getById(@PathVariable Long id) {
        return questionService.getQuestionById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public QuestionDto update(@PathVariable Long id,
                              @RequestBody QuestionDto dto) {
        return questionService.updateQuestion(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return "Deleted successfully";
    }

    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam MultipartFile file) {

        FileValidator.validateImage(file);

        String url = fileUploadService.uploadImage(file);

        return ResponseEntity.ok(url);
    }
}