package com.shall.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shall.student.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	Student findByStudentId(Long userId);

	@Modifying
	@Query("update Student u set u.firstName = ?1, u.lastName = ?2 , u.email = ?3, u.departmentId = ?4 where u.studentId = ?5")
	void updateCourse(String firstName, String lastName, String email, Long departmentId, Long studentId);
	
	@Query("SELECT s FROM Student s WHERE s.coursesIds = ''")
	List<Student> getStudentsWithNoCourses();
}
