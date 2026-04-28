package co.istad.quizera.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "classes")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Classroom name cannot be empty")
    @Size(max = 100, message = "Classroom name must be less than 100 characters")
    @Column(nullable = false, length = 100)
    private String name;

    @NotNull(message = "Teacher is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private User teacher;

    @NotBlank(message = "Class code is required")
    @Size(min = 4, max = 10, message = "Class code must be 4–10 characters")
    @Column(name = "class_code", nullable = false, unique = true, length = 10)
    private String classCode;

    @Builder.Default
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<ClassStudent> students = new HashSet<>();

    @OneToMany(mappedBy = "classroom")
    private List<Quiz> quizzes;

    @Builder.Default
    @Column(nullable = false)
    private Boolean deleted = false;
}