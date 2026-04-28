package co.istad.quizera.project.dto.classroom;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassroomResponse {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String classCode;

    @NotNull
    private String teacherName;

    @NotNull
    private Long teacherId;

    private Integer totalStudents;
}