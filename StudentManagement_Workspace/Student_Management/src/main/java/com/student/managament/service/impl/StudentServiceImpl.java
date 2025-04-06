package com.student.managament.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.student.managament.dto.StudentDto;
import com.student.managament.entity.Address;
import com.student.managament.entity.Course;
import com.student.managament.entity.Student;
import com.student.managament.repository.CourseRepository;
import com.student.managament.repository.StudentRepository;
import com.student.managament.service.StudentService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
    private CourseRepository courseRepository;

	@Override
	public List<StudentDto> getAllStudents() {
		List<Student> students = studentRepository.findAll();
		return students.stream().map(student -> modelMapper.map(student, StudentDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public StudentDto getStudentById(long studentId) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
		return modelMapper.map(student, StudentDto.class);
	}

	@Override
	public StudentDto saveStudent(StudentDto studentDto) {
		Student student = modelMapper.map(studentDto, Student.class);

		if (student.getAddresses() != null) {
			for (Address address : student.getAddresses()) {
				address.setStudent(student);
			}
		}

		Student savedStudent = studentRepository.save(student);
		return modelMapper.map(savedStudent, StudentDto.class);
	}

	@Override
	public StudentDto updateStudent(long studentId, StudentDto updatedStudentDto) {
		Student existingStudent = studentRepository.findById(studentId)
				.orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

		// You can also map full DTO to entity or manually update fields
		existingStudent.setName(updatedStudentDto.getName());
		existingStudent.setEmail(updatedStudentDto.getEmail());
		existingStudent.setGender(updatedStudentDto.getGender());

		Student updatedStudent = studentRepository.save(existingStudent);
		return modelMapper.map(updatedStudent, StudentDto.class);
	}

	@Override
	public void deleteStudentById(long studentId) {
		if (!studentRepository.existsById(studentId)) {
			throw new RuntimeException("Cannot delete. Student not found with id: " + studentId);
		}
		studentRepository.deleteById(studentId);
	}

	@Override
	public boolean verifyStudent(String studentCode, String studentBirthDate) {
		Optional<Student> student = studentRepository.findByStudentCodeAndDateOfBirth(studentCode, studentBirthDate);

		return student.isPresent();
	}

	public List<StudentDto> getStudentsByName(@RequestParam String name) {
		List<Student> students = studentRepository.findByNameContainingIgnoreCase(name);
		if (students.isEmpty()) {
			throw new RuntimeException("No students found with name: " + name);
		}

		return students.stream().map(student -> modelMapper.map(student, StudentDto.class))
				.collect(Collectors.toList());

	}

	public List<StudentDto> getStudentsByCourseName(@RequestParam String courseName) {
		List<Student> students = studentRepository.findStudentsByCourseName(courseName.replaceAll("\\s+", ""));
		if (students.isEmpty()) {
			throw new RuntimeException("No students found with courseName: " + courseName);
		}

		return students.stream().map(student -> modelMapper.map(student, StudentDto.class))
				.collect(Collectors.toList());
	}

	public List<Course> getEnrolledCourses(Long studentId) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
		return student.getCourses();
	}

	@Transactional
	public void unenrollFromCourse(Long studentId, Long courseId) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));

		student.getCourses().remove(course);
		//course.getStudent().remove(student);

		studentRepository.save(student);
		//courseRepository.save(course);
	}

}
