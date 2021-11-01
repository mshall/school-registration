package com.shall.student.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shall.student.entity.Student;
import com.shall.student.service.StudentService;
import com.shall.student.vo.CourseVO;
import com.shall.student.vo.ResponseTemplateVO;
import com.shall.student.vo.StudentRegistration;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {

	@Autowired
	private StudentService userService;

	@RequestMapping(value = "/", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
	public Student saveUser(@RequestBody Student user) {
		log.info("UserController.saveUser -> Start");
		return userService.saveUser(user);
	}

	@RequestMapping(value = "/", produces = "application/json; charset=UTF-8", method = RequestMethod.PUT)
	public int updateUser(@RequestBody Student student) {
		log.info("UserController.saveUser -> Start");
		return userService.updateStudent(student);
	}

	@RequestMapping(value = "/{id}", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
	public ResponseTemplateVO getStudent(@PathVariable("id") Long studentId) {
		log.info("UserController.getStudentWithCourses -> Start");
		return userService.getStudentById(studentId);
	}

	@RequestMapping(value = "/{id}", produces = "application/json; charset=UTF-8", method = RequestMethod.DELETE)
	public Boolean deleteStudentById(@PathVariable("id") Long studentId) {
		log.info("UserController.deleteStudentById -> Start");
		return userService.deleteStudentById(studentId);
	}
	
	@RequestMapping(value = "/{id}/courses", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
	public ArrayList<CourseVO> getStudentCourses(@PathVariable("id") Long studentId) {
		log.info("UserController.getStudentWithCourses -> Start");
		return userService.getStudentCourses(studentId);
	}

	@RequestMapping(value = "/registerCourse", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
	public ResponseTemplateVO registerCourse(@RequestBody StudentRegistration studentRegistration) {
		log.info("UserController.registerCourse -> Start");
		return userService.registerCourse(studentRegistration.getStudentId(), studentRegistration.getCourseId());
	}


	@RequestMapping(value = "/noCourses", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
	public List<Student> getStudentsWithNoCourses() {
		log.info("UserController.getStudentsWithNoCourses -> Start");
		return userService.getStudentsWithNoCourses();
	}
}