package com.shall.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shall.course.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	Course findByCourseId(Long departmentId);

	@Modifying
	@Query("update Course u set u.courseName = ?1, u.courseCode = ?2 where u.courseId = ?3")
	void updateCourse(String courseName, String courseCode, Long courseId);
	
	@Query("SELECT c FROM Course c WHERE c.registeredStudentsIds = ''")
	List<Course> getCoursesWithNoStudents();
}
