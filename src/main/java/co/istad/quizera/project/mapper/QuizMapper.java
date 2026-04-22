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

        return QuizResponse.builder()
                .id(quiz.getId())
                .title(quiz.getTitle())

                .classId(quiz.getClassroom() != null ? quiz.getClassroom().getId() : null)
                .createdById(quiz.getCreatedBy() != null ? quiz.getCreatedBy().getId() : null)

                .isPublic(quiz.getIsPublic())
                .createdAt(quiz.getCreatedAt())

                // FIX HERE
                .totalQuestions(
                        quiz.getQuestions() != null ? quiz.getQuestions().size() : 0
                )

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

    public List<QuestionDto> toQuestionDtoList(List<Question> questions) {
        return questions.stream()
                .map(this::toQuestionDto)
                .collect(Collectors.toList());
    }
}