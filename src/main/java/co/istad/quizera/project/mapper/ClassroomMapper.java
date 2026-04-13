package co.istad.quizera.project.mapper;

import co.istad.quizera.project.dto.classroom.ClassroomResponse;
import co.istad.quizera.project.entity.Classroom;
import org.springframework.stereotype.Component;

@Component
public class ClassroomMapper {

    public ClassroomResponse toDto(Classroom classroom) {
        return ClassroomResponse.builder()
                .id(classroom.getId())
                .name(classroom.getName())
                .classCode(classroom.getClassCode())
                .teacherName(classroom.getTeacher().getName())
                .teacherId(classroom.getTeacher().getId())
                .totalStudents(
                        classroom.getStudents() != null ? classroom.getStudents().size() : 0
                )
                .build();
    }
}
