package co.istad.quizera.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "flashcard_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlashcardItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Flashcard is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flashcard_id", nullable = false)
    private Flashcard flashcard;

    @NotBlank(message = "Front text is required")
    @Size(max = 500, message = "Front text must be less than 500 characters")
    @Column(name = "front_text", nullable = false, length = 500)
    private String frontText;

    @NotBlank(message = "Back text is required")
    @Size(max = 500, message = "Back text must be less than 500 characters")
    @Column(name = "back_text", nullable = false, length = 500)
    private String backText;

    @Size(max = 500, message = "Image URL must be less than 500 characters")
    @Column(name = "image_url", length = 500)
    private String imageUrl;
}