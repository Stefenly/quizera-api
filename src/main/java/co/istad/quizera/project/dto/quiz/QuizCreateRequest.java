//package co.istad.quizera.project.dto.quiz;
//
//import lombok.*;
//import java.util.List;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class QuizCreateRequest {
//    private String title;
//    private Long classId;
//    private List<QuestionDto> questions;
//    private Long classroomId;
//
//    private Boolean isPublic;
//}

package co.istad.quizera.project.dto.quiz;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizCreateRequest {

    @NotBlank
    @Size(max = 150)
    private String title;

    @NotNull
    private Long classroomId;

    @NotEmpty
    private List<QuestionDto> questions;

    @Builder.Default
    private Boolean isPublic = false;

    private Integer durationInSeconds;
}