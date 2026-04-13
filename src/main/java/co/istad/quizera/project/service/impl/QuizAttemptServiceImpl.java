package co.istad.quizera.project.service.impl;

import co.istad.quizera.project.dto.quiz.*;
import co.istad.quizera.project.entity.*;
import co.istad.quizera.project.enums.McqOption;
import co.istad.quizera.project.repository.*;
import co.istad.quizera.project.service.GamificationService;
import co.istad.quizera.project.service.QuizAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizAttemptServiceImpl implements QuizAttemptService {

    private final QuizAttemptRepository attemptRepository;
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final GamificationService gamificationService;

    // SUBMIT QUIZ (AUTO GRADING)
    @Override
    public QuizAttemptResultDto submitAttempt(QuizSubmitRequest request) {

        User user = getCurrentUser();

        Quiz quiz = quizRepository.findById(request.getQuizId())
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        // TIMER CHECK
        long now = System.currentTimeMillis();
        long elapsedSeconds = (now - request.getStartedAt()) / 1000;

        if (elapsedSeconds > quiz.getDurationInSeconds()) {
            throw new RuntimeException("Time is up! Quiz submission rejected.");
        }

        // GET QUESTIONS
        List<Question> questions = questionRepository.findByQuiz(quiz);

        Map<Long, Question> questionMap = questions.stream()
                .collect(Collectors.toMap(Question::getId, q -> q));

        int score = 0;

        List<QuestionResultDto> results = new ArrayList<>();

        for (QuizSubmitRequest.AnswerDto ans : request.getAnswers()) {

            Question q = questionMap.get(ans.getQuestionId());

            if (q == null) continue;

            boolean correct = q.getCorrectAnswer().equals(ans.getSelectedAnswer());

            if (correct) score++;

            results.add(QuestionResultDto.builder()
                    .questionId(q.getId())
                    .questionText(q.getQuestionText())
                    .correctAnswer(q.getCorrectAnswer())
                    .selectedAnswer(ans.getSelectedAnswer())
                    .isCorrect(correct)
                    .build());
        }

        // GAMIFICATION LOGIC (ADD HERE)
        GamificationResultDto gamification = gamificationService.calculate(
                score,
                questions.size(),
                3, // streak (temporary, can improve later)
                user.getTotalXP()
        );

        // update user XP
        user.setTotalXP(gamification.getTotalXP());
        userRepository.save(user);

        // SAVE ATTEMPT
        QuizAttempt attempt = QuizAttempt.builder()
                .user(user)
                .quiz(quiz)
                .score(score)
                .totalQuestions(questions.size())
                .build();

        attemptRepository.save(attempt);

        // RETURN RESULT
        return QuizAttemptResultDto.builder()
                .id(attempt.getId())
                .quizId(quiz.getId())
                .score(score)
                .totalQuestions(questions.size())
                .completedAt(attempt.getCompletedAt())
                .results(results)
                .gamification(gamification)
                .build();
    }

    // GET LOGGED IN USER
    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // HISTORY APIs
    @Override
    public List<QuizAttemptDto> getAllAttempts() {
        return attemptRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public QuizAttemptDto getAttemptById(Long id) {
        QuizAttempt attempt = attemptRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attempt not found"));

        return mapToDto(attempt);
    }

    @Override
    public List<QuizAttemptDto> getAttemptsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return attemptRepository.findByUser(user)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public List<QuizAttemptDto> getAttemptsByQuiz(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        return attemptRepository.findByQuiz(quiz)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public QuizAttemptDto updateAttempt(Long id, QuizAttemptDto dto) {

        QuizAttempt attempt = attemptRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attempt not found"));

        if (dto.getScore() != null)
            attempt.setScore(dto.getScore());

        if (dto.getTotalQuestions() != null)
            attempt.setTotalQuestions(dto.getTotalQuestions());

        return mapToDto(attemptRepository.save(attempt));
    }

    @Override
    public void deleteAttempt(Long id) {
        attemptRepository.deleteById(id);
    }

    // MAPPER
    private QuizAttemptDto mapToDto(QuizAttempt a) {
        return QuizAttemptDto.builder()
                .id(a.getId())
                .quizId(a.getQuiz().getId())
                .score(a.getScore())
                .totalQuestions(a.getTotalQuestions())
                .completedAt(a.getCompletedAt())
                .build();
    }
}