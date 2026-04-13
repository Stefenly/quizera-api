package co.istad.quizera.project.controller;

import co.istad.quizera.project.dto.classroom.ClassroomCreateRequest;
import co.istad.quizera.project.dto.classroom.ClassroomResponse;
import co.istad.quizera.project.dto.classroom.JoinClassRequest;
import co.istad.quizera.project.entity.Classroom;
import co.istad.quizera.project.entity.User;
import co.istad.quizera.project.service.ClassroomService;
import co.istad.quizera.project.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
public class ClassroomController {

    private final ClassroomService classroomService;

    @PostMapping("/{teacherId}")
    public ClassroomResponse create(@PathVariable Long teacherId,
                                    @RequestBody ClassroomCreateRequest request) {
        return classroomService.createClassroom(teacherId, request);
    }

    @GetMapping("/{id}")
    public ClassroomResponse getById(@PathVariable Long id) {
        return classroomService.getClassroomById(id);
    }

    @GetMapping("/teacher/{teacherId}")
    public List<ClassroomResponse> teacherClasses(@PathVariable Long teacherId) {
        return classroomService.getTeacherClasses(teacherId);
    }

    @GetMapping("/student/{studentId}")
    public List<ClassroomResponse> studentClasses(@PathVariable Long studentId) {
        return classroomService.getStudentClasses(studentId);
    }

    @PostMapping("/join/{studentId}")
    public ClassroomResponse join(@PathVariable Long studentId,
                                  @RequestBody JoinClassRequest request) {
        return classroomService.joinClassroom(studentId, request.getClassCode());
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        classroomService.deleteClassroom(id);
        return "Deleted";
    }
}