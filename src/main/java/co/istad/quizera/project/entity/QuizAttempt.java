package co.istad.quizera.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "attempts",
        indexes = {
                @Index(name = "idx_attempt_user", columnList = "user_id"),
                @Index(name = "idx_attempt_quiz", columnList = "quiz_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "User is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull(message = "Quiz is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @NotNull(message = "Score is required")
    @Min(value = 0, message = "Score cannot be negative")
    @Column(nullable = false)
    private Integer score;

    @NotNull(message = "Total questions is required")
    @Min(value = 1, message = "Total questions must be at least 1")
    @Column(nullable = false)
    private Integer totalQuestions;

    @Column(name = "completed_at", nullable = false, updatable = false)
    private LocalDateTime completedAt;

    @PrePersist
    public void prePersist() {
        this.completedAt = LocalDateTime.now();
    }
}