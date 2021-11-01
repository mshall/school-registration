package com.shall.course.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentVO {

    private Long studentId;
    private String firstName;
    private String lastName;
    private String email;
    private Long departmentId;
    private String coursesIds;
}
