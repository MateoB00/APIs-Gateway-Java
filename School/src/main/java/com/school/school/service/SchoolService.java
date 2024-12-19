package com.school.school.service;

import com.school.school.dto.StudentDto;
import com.school.school.entity.School;
import com.school.school.repository.SchoolRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SchoolService {
    private SchoolRepository schoolRepository;

    public List<School> findAll() {
        return schoolRepository.findAll();
    }

    public Optional<School> findById(Long id) {
        return schoolRepository.findById(id);
    }

    public School save(School school) {
        return schoolRepository.save(school);
    }

    public void deleteById(Long id) {
        schoolRepository.deleteById(id);
    }
}
