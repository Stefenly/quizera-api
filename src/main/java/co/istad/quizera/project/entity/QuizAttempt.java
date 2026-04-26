package co.istad.quizera.project.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//@Table(name = "attempts")
//public class QuizAttempt {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "quiz_id")
//    @org.hibernate.annotations.NotFound(action = org.hibernate.annotations.NotFoundAction.IGNORE)
//    private Quiz quiz;
//
//    @Column(nullable = false)
//    private Integer score;
//
//    @Column(nullable = false)
//    private Integer totalQuestions;
//
//    @Builder.Default
//    @Column(name = "completed_at", nullable = false)
//    private LocalDateTime completedAt = LocalDateTime.now();
//}


@Entity
@Table(name = "attempts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false)
    private Integer totalQuestions;

    @Column(name = "completed_at", nullable = false)
    private LocalDateTime completedAt;

    @PrePersist
    public void prePersist() {
        this.completedAt = LocalDateTime.now();
    }
}