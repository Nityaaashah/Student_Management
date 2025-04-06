package com.student.managament.repository;

import org.springframework.stereotype.Repository;

import com.student.managament.entity.Address;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AddressRepository extends JpaRepository<Address ,Long> {

}
