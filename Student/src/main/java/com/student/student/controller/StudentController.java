package com.student.student.controller;

import com.student.student.entity.Student;
import com.student.student.entity.StudentWithSchool;
import com.student.student.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class StudentController {
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentWithoutSchoolById(@PathVariable String id) {
        return studentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/school/{id}")
    public ResponseEntity<StudentWithSchool> getStudentWithSchool(@PathVariable String id) {
        Optional<StudentWithSchool> studentWithSchool = studentService.findStudentWithSchoolById(id);

        return studentWithSchool
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.save(student));
    }
}
