package com.student.managament.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.managament.dto.StudentDto;
import com.student.managament.entity.Course;
import com.student.managament.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping
	 @Operation(summary = "Get all students", description = "Retrieve a list of all registered students")
	public ResponseEntity<Object> getAllStudentDetails() {
		return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	 @Operation(summary = "Get student by ID", description = "Retrieve student details by their unique ID")
	public ResponseEntity<Object> getStudentById(@PathVariable("id") long id) {
		return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
	}

	@PostMapping
	@Operation(summary = "Create new student", description = "Add a new student to the system")
	public ResponseEntity<Object> createStudent(@RequestBody @Valid StudentDto student) {
		return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update student by ID", description = "Update an existing student's details after verifying identity")
	public ResponseEntity<Object> updateStudentById(@PathVariable("id") long id,
			@RequestBody @Valid StudentDto student) {

		boolean isVerified = studentService.verifyStudent(student.getStudentCode(), student.getDateOfBirth());
		if (!isVerified) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Student identity not verified");
		}

		return new ResponseEntity<>(studentService.updateStudent(id, student), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete student by ID", description = "Delete a student from the system by ID")
	public ResponseEntity<Object> deleteStudent(@PathVariable("id") long id) {
		studentService.deleteStudentById(id);
		return ResponseEntity.ok("Student Deleted successfully");
	}

	@GetMapping("/search/by-name")
	@Operation(summary = "Search students by name", description = "Find students based on their name")
	public ResponseEntity<Object> getStudentsByName(@RequestParam String name) {
		return ResponseEntity.ok(studentService.getStudentsByName(name));
	}

	@GetMapping("/search/by-course")
	@Operation(summary = "Search students by course", description = "Find students enrolled in a specific course")
	public ResponseEntity<Object> getStudentsByCourse(@RequestParam String courseName) {
		return ResponseEntity.ok(studentService.getStudentsByCourseName(courseName));
	}

	@GetMapping("/search/by-topics/{studentId}")
	@Operation(summary = "Get enrolled courses", description = "Retrieve all courses a student is enrolled in")
		public ResponseEntity<Object> getEnrolledCourses(@PathVariable Long studentId) {
		List<Course> courses = studentService.getEnrolledCourses(studentId);
		return ResponseEntity.ok(courses);
	}

	@DeleteMapping("unenrolled/{studentId}/courses/{courseId}")
	@Operation(summary = "Unenroll from course", description = "Remove a student's enrollment from a specific course")
	public ResponseEntity<String> unenrollFromCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
		studentService.unenrollFromCourse(studentId, courseId);
		return ResponseEntity.ok("Unenrolled from course successfully.");
	}
}
