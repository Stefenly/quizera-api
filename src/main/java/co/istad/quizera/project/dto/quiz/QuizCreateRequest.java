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

import co.istad.quizera.project.enums.Visibility;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizCreateRequest {
//
//    @NotBlank
//    @Size(max = 150)
//    private String title;
//
//    @NotNull
//    private Long classroomId;
//
//    @NotEmpty
//    private List<QuestionDto> questions;
//
//    @Builder.Default
//    private Boolean isPublic = false;
//
//    private Integer durationInSeconds;


    @NotBlank(message = "Title is required")
    @Size(max = 150, message = "Title must be less than 150 characters")
    private String title;

    @NotNull(message = "Classroom ID is required")
    private Long classroomId;

    @NotEmpty(message = "Quiz must have at least one question")
    private List<@Valid QuestionDto> questions;

    private Visibility visibility;

    @Min(value = 10, message = "Minimum duration is 10 seconds")
    @Max(value = 86400, message = "Maximum duration is 24 hours")
    private Integer durationInSeconds;
}