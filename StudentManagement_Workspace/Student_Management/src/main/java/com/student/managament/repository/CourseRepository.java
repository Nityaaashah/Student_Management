package com.student.managament.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.managament.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
