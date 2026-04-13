package co.istad.quizera.project.service;

import co.istad.quizera.project.dto.classroom.ClassroomCreateRequest;
import co.istad.quizera.project.dto.classroom.ClassroomResponse;
import co.istad.quizera.project.entity.Classroom;
import co.istad.quizera.project.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClassroomService {

    ClassroomResponse createClassroom(Long teacherId, ClassroomCreateRequest request);

    ClassroomResponse getClassroomById(Long id);

    List<ClassroomResponse> getTeacherClasses(Long teacherId);

    List<ClassroomResponse> getStudentClasses(Long studentId);

    ClassroomResponse joinClassroom(Long studentId, String classCode);

    void deleteClassroom(Long id);
}