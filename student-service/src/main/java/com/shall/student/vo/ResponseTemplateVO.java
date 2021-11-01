package com.shall.student.vo;

import java.util.ArrayList;

import com.shall.student.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {

	private Student student;
	private ArrayList<CourseVO>  courses;
}
