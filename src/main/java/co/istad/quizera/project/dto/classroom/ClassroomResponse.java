package co.istad.quizera.project.dto.classroom;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassroomResponse {
    private Long id;
    private String name;
    private String classCode;
    private String teacherName;
    private Long teacherId;
    private int totalStudents;
}