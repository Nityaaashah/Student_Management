package com.student.managament.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.student.managament.dto.CourseDto;
import com.student.managament.service.CourseService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@GetMapping
	@Operation(summary = "Get all courses", description = "Retrieve a list of all available courses")
	public ResponseEntity<Object> getAllCourseDetails() {
		return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get course by ID", description = "Retrieve a specific course by its unique ID")
	public ResponseEntity<Object> getCourseById(@PathVariable("id") long id) {
		return new ResponseEntity<>(courseService.getCourseById(id), HttpStatus.OK);
	}

	@PostMapping
	@Operation(summary = "Create a new course", description = "Add a new course to the system")
	public ResponseEntity<Object> createCourse(@RequestBody @Valid CourseDto course) {
		return new ResponseEntity<>(courseService.saveCourse(course), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update course by ID", description = "Update details of an existing course by ID")
	public ResponseEntity<Object> updateCourseById(@PathVariable("id") long id, @RequestBody @Valid CourseDto course) {
		return new ResponseEntity<>(courseService.updateCourse(id, course), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete course by ID", description = "Remove a course from the system by its ID")
	public ResponseEntity<Object> deleteCourse(@PathVariable("id") long id) {
		courseService.deleteCourseById(id);
		return ResponseEntity.ok("Course Deleted successfully");
	}

}
