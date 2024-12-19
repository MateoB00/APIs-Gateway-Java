package com.school.school.controller;

import com.school.school.entity.School;
import com.school.school.service.SchoolService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class SchoolController {
    private SchoolService schoolService;

    @GetMapping
    public List<School> getAllSchools() {
        return schoolService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<School> getSchoolById(@PathVariable Long id) {
        return schoolService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<School> createSchool(@RequestBody School school) {
        return ResponseEntity.ok(schoolService.save(school));
    }

    @PutMapping("/{id}")
    public ResponseEntity<School> updateSchool(@PathVariable Long id, @RequestBody School schoolDetails) {
        return schoolService.findById(id)
                .map(existingSchool -> {
                    existingSchool.setName(schoolDetails.getName());
                    existingSchool.setAddress(schoolDetails.getAddress());
                    School updatedSchool = schoolService.save(existingSchool);
                    return ResponseEntity.ok(updatedSchool);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSchool(@PathVariable Long id) {
        schoolService.deleteById(id);
    }
}
