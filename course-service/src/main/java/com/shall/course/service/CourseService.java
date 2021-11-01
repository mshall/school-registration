package com.shall.course.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.shall.course.entity.Course;
import com.shall.course.repository.CourseRepository;
import com.shall.course.vo.StudentVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CourseService {

	@Autowired
	private RestTemplate restTemplate;
	
	
	@Autowired
	private CourseRepository courseRepository;

	public Course saveCourse(Course course) {
		log.info("CourseService.saveCourse -> Start");
		return courseRepository.save(course);
	}

	public Course findCourseById(Long courseId) {
		log.info("CourseService.findCourseById -> Start");
		return courseRepository.findByCourseId(courseId);
	}

	public boolean deleteCourseById(Long courseId) {
		log.info("CourseService.deleteCourseById -> Start");
		try {
			courseRepository.deleteById(courseId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Course updateCourse(Course course) {
		try {
			courseRepository.updateCourse(course.getCourseName(), course.getCourseCode(), course.getCourseId());
			return course;
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Course> getCoursesWithNoStudents(){
		return courseRepository.getCoursesWithNoStudents();
	}
	
	public List<StudentVO> getStudentsForCourse(Long courseId){
		List<StudentVO> students = new ArrayList<>();
		Course  course = findCourseById(courseId);
		if(course == null || course.getRegisteredStudentsIds() == null || course.getRegisteredStudentsIds().trim().isEmpty()) {
			return students;
		}
		String[] registeredStudentsIds = course.getRegisteredStudentsIds().split(",");
		StudentVO student = null;
		for(String studentId : registeredStudentsIds) {
			student = getStudent(Long.parseLong(studentId));
			students.add(student);
		}
		return students;
	}
	
	public StudentVO getStudent(Long studentId) {
		return restTemplate.getForObject("http://STUDENT-SERVICE/course/" + studentId, StudentVO.class);
	}
}
