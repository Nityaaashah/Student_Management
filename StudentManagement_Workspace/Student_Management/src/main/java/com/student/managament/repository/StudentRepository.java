package com.student.managament.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.student.managament.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	Optional<Student> findByStudentCodeAndDateOfBirth(String studentCode,String dateOfBirth);
	
	List<Student> findByNameContainingIgnoreCase(String name);
	
	@Query("SELECT s FROM Student s JOIN s.courses c WHERE c.name = :courseName")
    List<Student> findStudentsByCourseName(@Param("courseName") String courseName);
}
