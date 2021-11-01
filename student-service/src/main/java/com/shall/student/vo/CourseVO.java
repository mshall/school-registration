package com.shall.student.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseVO {

	private Long courseId;
	private String courseName;
	private String courseCode;
	private String registeredStudentsIds;
}