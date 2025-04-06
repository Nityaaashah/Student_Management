package com.student.managament.service;

import java.util.List;

import com.student.managament.dto.StudentDto;
import com.student.managament.entity.Course;


public interface StudentService {

	List<StudentDto> getAllStudents();

	StudentDto getStudentById(long studentId);

	StudentDto saveStudent(StudentDto student);

	StudentDto updateStudent(long studentId, StudentDto student);

	void deleteStudentById(long studentId);

	boolean verifyStudent(String studentCode, String studentBirthDate);

	List<StudentDto> getStudentsByName(String name);

	List<StudentDto> getStudentsByCourseName(String name);

	List<Course> getEnrolledCourses(Long studentId);

	void unenrollFromCourse(Long studentId, Long courseId);
}
