package co.istad.quizera.project.entity;

import co.istad.quizera.project.enums.McqOption;
import co.istad.quizera.project.enums.QuestionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Quiz is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @NotNull(message = "Question type is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionType type;

    @NotBlank(message = "Question text is required")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String questionText;

    @Size(max = 255)
    private String optionA;

    @Size(max = 255)
    private String optionB;

    @Size(max = 255)
    private String optionC;

    @Size(max = 255)
    private String optionD;

    @NotNull(message = "Correct answer is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private McqOption correctAnswer;

    @Size(max = 500)
    private String imageUrl;
}