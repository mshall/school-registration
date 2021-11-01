package com.shall.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shall.course.entity.Course;
import com.shall.course.service.CourseService;
import com.shall.course.vo.StudentVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/course")
@Slf4j
public class CourseController {

	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "/", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
	public Course saveCourse(@RequestBody Course course) {
		log.info("CourseController.saveCourse -> Start");
		return courseService.saveCourse(course);
	}

	@RequestMapping(value = "/", produces = "application/json; charset=UTF-8", method = RequestMethod.PUT)
	public Course updateCourse(@RequestBody Course course) {
		log.info("CourseController.updateCourse -> Start");
		return courseService.updateCourse(course);
	}

	@RequestMapping(value = "/{id}", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
	public Course findCourseById(@PathVariable("id") Long courseId) {
		log.info("CourseController.findDepartmentById -> Start");
		return courseService.findCourseById(courseId);
	}
	
	@RequestMapping(value = "/{id}/students", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
	public List<StudentVO> getStudentsForCourse(@PathVariable("id") Long courseId) {
		log.info("CourseController.findDepartmentById -> Start");
		return courseService.getStudentsForCourse(courseId);
	}

	@RequestMapping(value = "/{id}", produces = "application/json; charset=UTF-8", method = RequestMethod.DELETE)
	public Boolean deleteCourseById(@PathVariable("id") Long courseId) {
		log.info("CourseController.findDepartmentById -> Start");
		return courseService.deleteCourseById(courseId);
	}
	
	@RequestMapping(value = "/noStudents", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
	public List<Course> getCoursesWithNoStudents() {
		log.info("CourseController.getCoursesWithNoStudents -> Start");
		return courseService.getCoursesWithNoStudents();
	}

}
