package co.istad.quizera.project.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

//@Entity
//@Table(name = "quizzes")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class Quiz {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false, length = 150)
//    private String title;
//
//    @ManyToOne
//    @JoinColumn(name = "class_id", nullable = false)
//    private Classroom classroom;
//
//    @ManyToOne
//    @JoinColumn(name = "created_by", nullable = false)
//    private User createdBy;
//
//    @Builder.Default
//    @Column(name = "is_public", nullable = false)
//    private Boolean isPublic = false;
//
//    @Builder.Default
//    @Column(name = "created_at", nullable = false)
//    private LocalDateTime createdAt = LocalDateTime.now();
//
//    @Builder.Default
//    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Question> questions = new HashSet<>();
//
//    @OneToMany(mappedBy = "quiz")
//    private Set<QuizAttempt> attempts;
//
//    private Integer durationInSeconds;
//}


@Entity
@Table(name = "quizzes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", nullable = false)
    private Classroom classroom;

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