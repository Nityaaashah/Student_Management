package com.student.managament.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.managament.dto.AddressDto;
import com.student.managament.service.AddressService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
@RestController
@RequestMapping(value = "/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    @Operation(summary = "Get all addresses", description = "Retrieve all address records from the system")
    public ResponseEntity<List<AddressDto>> getAllAddressDetails() {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get address by ID", description = "Retrieve a specific address using its ID")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable long id) {
        return ResponseEntity.ok(addressService.getAddressById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new address", description = "Add a new address record to the system")
    public ResponseEntity<AddressDto> createAddress(@RequestBody @Valid AddressDto addressDto) {
        return ResponseEntity.ok(addressService.saveAddress(addressDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update address by ID", description = "Update the details of an existing address by its ID")
    public ResponseEntity<AddressDto> updateAddressById(@PathVariable long id,
                                                        @RequestBody @Valid AddressDto addressDto) {
        return ResponseEntity.ok(addressService.updateAddress(id, addressDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete address by ID", description = "Delete an address from the system using its ID")
    public ResponseEntity<String> deleteAddress(@PathVariable long id) {
        addressService.deleteAddressById(id);
        return ResponseEntity.ok("Address deleted successfully");
    }
}