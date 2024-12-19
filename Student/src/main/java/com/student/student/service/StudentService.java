package com.student.student.service;

import com.student.student.dto.SchoolDto;
import com.student.student.entity.Student;
import com.student.student.entity.StudentWithSchool;
import com.student.student.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;
    private RestTemplate restTemplate;

    private final String SCHOOL_SERVICE_URL = "http://school-application";

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(String id) {
        return studentRepository.findById(id);
    }

    public Optional<StudentWithSchool> findStudentWithSchoolById(String id) {
        return studentRepository.findById(id).map(student -> {
            SchoolDto school = fetchSchoolById(student.getIdSchool());
            return new StudentWithSchool(student, school);
        });
    }

    private SchoolDto fetchSchoolById(String idSchool) {
        String url = SCHOOL_SERVICE_URL + "/" + idSchool;
        return restTemplate.getForObject(url, SchoolDto.class);
    }
}
