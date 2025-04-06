package com.student.managament.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.managament.dto.CourseDto;
import com.student.managament.entity.Course;
import com.student.managament.repository.CourseRepository;
import com.student.managament.service.CourseService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(course -> modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CourseDto> getCourseById(long courseId) {
        return courseRepository.findById(courseId)
                .map(course -> modelMapper.map(course, CourseDto.class));
    }

    @Override
    public CourseDto saveCourse(CourseDto courseDto) {
        Course course = modelMapper.map(courseDto, Course.class);
        Course savedCourse = courseRepository.save(course);
        return modelMapper.map(savedCourse, CourseDto.class);
    }

    @Override
    public CourseDto updateCourse(long courseId, CourseDto courseDto) {
        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));

        existingCourse.setName(courseDto.getName());
        existingCourse.setType(courseDto.getType());
        existingCourse.setTopics(courseDto.getTopics());
        existingCourse.setDescription(courseDto.getDescription());
        existingCourse.setDuration(courseDto.getDuration());

        Course updatedCourse = courseRepository.save(existingCourse);
        return modelMapper.map(updatedCourse, CourseDto.class);
    }

    @Override
    public void deleteCourseById(long courseId) {
        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));

        courseRepository.delete(existingCourse);
    }

   
}
