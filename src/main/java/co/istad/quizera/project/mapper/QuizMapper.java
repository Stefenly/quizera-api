package co.istad.quizera.project.mapper;

import co.istad.quizera.project.dto.quiz.QuestionDto;
import co.istad.quizera.project.dto.quiz.QuizResponse;
import co.istad.quizera.project.entity.Question;
import co.istad.quizera.project.entity.Quiz;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuizMapper {

    public QuizResponse toDto(Quiz quiz) {
        if (quiz == null) return null;

        return QuizResponse.builder()
                .id(quiz.getId())
                .title(quiz.getTitle())
                .classroomId(
                        quiz.getClassroom() != null ? quiz.getClassroom().getId() : null
                )
                .createdById(
                        quiz.getCreatedBy() != null ? quiz.getCreatedBy().getId() : null
                )
                .visibility(quiz.getVisibility())
                .createdAt(quiz.getCreatedAt())
                .totalQuestions(
                        quiz.getQuestions() != null ? quiz.getQuestions().size() : 0
                )
                .durationInSeconds(quiz.getDurationInSeconds())
                .build();
    }

    public QuestionDto toQuestionDto(Question q) {
        return QuestionDto.builder()
                .id(q.getId())
                .type(q.getType())
                .questionText(q.getQuestionText())
                .optionA(q.getOptionA())
                .optionB(q.getOptionB())
                .optionC(q.getOptionC())
                .optionD(q.getOptionD())
                .correctAnswer(q.getCorrectAnswer())
                .imageUrl(q.getImageUrl())
                .build();
    }

    public Question toEntity(QuestionDto dto) {
        return Question.builder()
                .questionText(dto.getQuestionText())
                .type(dto.getType())
                .optionA(dto.getOptionA())
                .optionB(dto.getOptionB())
                .optionC(dto.getOptionC())
                .optionD(dto.getOptionD())
                .correctAnswer(dto.getCorrectAnswer())
                .imageUrl(dto.getImageUrl())
                .build();
    }

    public List<QuestionDto> toQuestionDtoList(List<Question> questions) {
        if (questions == null) return List.of();

        return questions.stream()
                .map(this::toQuestionDto)
                .toList();
    }
}