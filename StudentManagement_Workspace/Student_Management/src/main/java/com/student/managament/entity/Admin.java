package com.student.managament.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "admins")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Admin {
    @Id @GeneratedValue
    private Long id;

    private String username;
    private String password;

}