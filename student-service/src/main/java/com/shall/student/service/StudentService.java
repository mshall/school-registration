package com.shall.student.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.shall.student.entity.Student;
import com.shall.student.repository.StudentRepository;
import com.shall.student.vo.CourseVO;
import com.shall.student.vo.ResponseTemplateVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private RestTemplate restTemplate;

	public Student saveUser(Student student) {
		log.info("UserService.saveStudent -> Start");
		return studentRepository.save(student);
	}

	public ResponseTemplateVO getStudentById(Long studentId) {
		log.info("StudentService.getStudentById -> Start");
		ResponseTemplateVO vo = new ResponseTemplateVO();
		Student student = studentRepository.findByStudentId(studentId);

		ArrayList<CourseVO> courseVOList = getStudentCourses(studentId);
		vo.setStudent(student);
		vo.setCourses(courseVOList);
		return vo;
	}

	public boolean deleteStudentById(Long StudentId) {
		try {
			studentRepository.deleteById(StudentId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public int updateStudent(Student student) {
		try {
			studentRepository.updateCourse(student.getFirstName(), student.getLastName(), student.getEmail(),
					student.getDepartmentId(), student.getStudentId());
			return 1;
		} catch (Exception e) {

			return 0;
		}
	}

	public ResponseTemplateVO registerCourse(Long studentId, Long courseId) {
		ResponseTemplateVO finalStudentRegisterResponse = new ResponseTemplateVO();
		ResponseTemplateVO studentTVO = getStudentById(studentId);
		int registerStudents = getNumberOfRegisteredStudents(courseId);
		if (studentTVO != null && studentTVO.getStudent() != null && studentTVO.getCourses().size() < 5
				&& registerStudents < 50) {
			Student student = studentTVO.getStudent();
			student.setCoursesIds(courseId + "," + student.getCoursesIds());
			updateStudent(student);
			finalStudentRegisterResponse = getStudentById(studentId);
			// Now update the course students
			addStudentToCourse(studentId, courseId);
		}

		return finalStudentRegisterResponse;
	}

	public int getNumberOfRegisteredStudents(Long courseId) {
		CourseVO course = getCourse(courseId);
		String[] studentsNumber = course.getRegisteredStudentsIds().split(",");
		return studentsNumber.length;

	}

	public CourseVO addStudentToCourse(Long studentId, Long courseId) {
		CourseVO course = getCourse(courseId);
		course.setRegisteredStudentsIds(studentId + "," + course.getRegisteredStudentsIds());
		restTemplate.put("http://Course-SERVICE/course/", course, CourseVO.class);
		return course;
	}

	public CourseVO getCourse(Long courseId) {
		return restTemplate.getForObject("http://COURSE-SERVICE/course/" + courseId, CourseVO.class);
	}

	public ArrayList<CourseVO> getStudentCourses(Long studentId) {
		ArrayList<CourseVO> coursesList = new ArrayList<>();
		CourseVO courseVO = null;
		Student student = studentRepository.findByStudentId(studentId);
		if (student == null || student.getCoursesIds() == null || student.getCoursesIds().trim().isEmpty()) {
			return coursesList;
		}
		String[] coursesIds = student.getCoursesIds().split(",");
		for (String courseId : coursesIds) {
			courseVO = getCourse(Long.parseLong(courseId));
			coursesList.add(courseVO);
		}
		return coursesList;
	}

	public List<Student> getStudentsWithNoCourses() {
		return studentRepository.getStudentsWithNoCourses();
	}

}
