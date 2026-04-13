package co.istad.quizera.project.service.impl;

import co.istad.quizera.project.dto.quiz.QuestionDto;
import co.istad.quizera.project.entity.Question;
import co.istad.quizera.project.entity.Quiz;
import co.istad.quizera.project.mapper.QuizMapper;
import co.istad.quizera.project.repository.QuestionRepository;
import co.istad.quizera.project.repository.QuizRepository;
import co.istad.quizera.project.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;

    // CREATE
    @Override
    public QuestionDto createQuestion(Long quizId, QuestionDto dto) {

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        Question question = Question.builder()
                .quiz(quiz)
                .type(dto.getType())
                .questionText(dto.getQuestionText())
                .optionA(dto.getOptionA())
                .optionB(dto.getOptionB())
                .optionC(dto.getOptionC())
                .optionD(dto.getOptionD())
                .correctAnswer(dto.getCorrectAnswer())
                .imageUrl(dto.getImageUrl())
                .build();

        questionRepository.save(question);

        return quizMapper.toQuestionDto(question);
    }

    // GET BY QUIZ
    @Override
    public List<QuestionDto> getQuestionsByQuiz(Long quizId) {

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        return questionRepository.findByQuiz(quiz)
                .stream()
                .map(quizMapper::toQuestionDto)
                .toList();
    }

     //CREATE
     @Override
     public List<QuestionDto> createQuestions(Long quizId, List<QuestionDto> dtos) {

         Quiz quiz = quizRepository.findById(quizId)
                 .orElseThrow(() -> new RuntimeException("Quiz not found"));

         return dtos.stream().map(dto -> {

             Question question = Question.builder()
                     .quiz(quiz)
                     .type(dto.getType())
                     .questionText(dto.getQuestionText())
                     .optionA(dto.getOptionA())
                     .optionB(dto.getOptionB())
                     .optionC(dto.getOptionC())
                     .optionD(dto.getOptionD())
                     .correctAnswer(dto.getCorrectAnswer())
                     .imageUrl(dto.getImageUrl())
                     .build();

             questionRepository.save(question);

             return quizMapper.toQuestionDto(question);

         }).toList();
     }

    // GET BY ID
    @Override
    public QuestionDto getQuestionById(Long id) {

        Question q = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        return quizMapper.toQuestionDto(q);
    }

    // UPDATE
    @Override
    public QuestionDto updateQuestion(Long id, QuestionDto dto) {

        Question q = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        if (dto.getQuestionText() != null)
            q.setQuestionText(dto.getQuestionText());

        if (dto.getOptionA() != null)
            q.setOptionA(dto.getOptionA());

        if (dto.getOptionB() != null)
            q.setOptionB(dto.getOptionB());

        if (dto.getOptionC() != null)
            q.setOptionC(dto.getOptionC());

        if (dto.getOptionD() != null)
            q.setOptionD(dto.getOptionD());

        if (dto.getCorrectAnswer() != null)
            q.setCorrectAnswer(dto.getCorrectAnswer());

        if (dto.getImageUrl() != null)
            q.setImageUrl(dto.getImageUrl());

        if (dto.getType() != null)
            q.setType(dto.getType());

        return quizMapper.toQuestionDto(questionRepository.save(q));
    }

    // DELETE
    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}