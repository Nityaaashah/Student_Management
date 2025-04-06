package com.student.managament.service;

import java.util.List;
import java.util.Optional;

import com.student.managament.dto.CourseDto;
import com.student.managament.entity.Course;

public interface CourseService {

    List<CourseDto> getAllCourses();

    Optional<CourseDto> getCourseById(long courseId);

    CourseDto saveCourse(CourseDto course);

    CourseDto updateCourse(long courseId, CourseDto course);

    void deleteCourseById(long courseId);
}
