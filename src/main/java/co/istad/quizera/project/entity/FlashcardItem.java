package co.istad.quizera.project.entity;

import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "flashcard_id")
    private Flashcard flashcard;

    private String frontText;
    private String backText;
    private String imageUrl;
}