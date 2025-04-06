package com.student.managament.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
	
    private Long id;
    private String name;
    private String dateOfBirth;
    private String gender;
    private String studentCode;
    private String email;
    private String mobileNumber;
    private String parentsNames;

    private List<AddressDto> addresses;

    private List<CourseDto> courses;
}
