package com.student.managament.service;

import java.util.List;
import java.util.Optional;

import com.student.managament.dto.AddressDto;
import com.student.managament.entity.Address;

public interface AddressService {

    List<AddressDto> getAllAddresses();

    AddressDto getAddressById(long addressId);

    AddressDto saveAddress(AddressDto address);

    AddressDto updateAddress(long addressId, AddressDto address);

    void deleteAddressById(Long id);

}
