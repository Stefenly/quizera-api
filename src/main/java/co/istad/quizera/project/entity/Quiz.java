package co.istad.quizera.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "quizzes",
        indexes = {
                @Index(name = "idx_quiz_class", columnList = "class_id"),
                @Index(name = "idx_quiz_creator", columnList = "created_by")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 150, message = "Title must be less than 150 characters")
    @Column(nullable = false, length = 150)
    private String title;

    @NotNull(message = "Classroom is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", nullable = false)
    private Classroom classroom;

    @NotNull(message = "Creator is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @Builder.Default
    @Column(name = "is_public", nullable = false)
    private Boolean isPublic = false;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Builder.Default
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Question> questions = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "quiz")
    private Set<QuizAttempt> attempts = new HashSet<>();

    @Min(value = 10, message = "Duration must be at least 10 seconds")
    @Max(value = 7200, message = "Duration cannot exceed 2 hours")
    @Column(name = "duration_seconds")
    private Integer durationInSeconds;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
    public void addQuestion(Question q) {
        questions.add(q);
        q.setQuiz(this);
    }
}