package co.istad.quizera.project.entity;

import co.istad.quizera.project.enums.Visibility;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "flashcards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 150, message = "Title must be less than 150 characters")
    @Column(nullable = false, length = 150)
    private String title;

    @NotNull(message = "Visibility is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Visibility visibility;

    @NotNull(message = "Creator is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @Builder.Default
    @OneToMany(
            mappedBy = "flashcard",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<FlashcardItem> items = new HashSet<>();
}