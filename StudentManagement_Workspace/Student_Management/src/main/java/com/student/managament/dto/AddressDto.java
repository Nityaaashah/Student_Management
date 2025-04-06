package com.student.managament.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {

    private Long id;
    private String type;    
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private Long studentId;  
}
