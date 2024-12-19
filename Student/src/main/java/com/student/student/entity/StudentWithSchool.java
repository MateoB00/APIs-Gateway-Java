package com.student.student.entity;

import com.student.student.dto.SchoolDto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentWithSchool {
    private Student student;
    private SchoolDto school;
}