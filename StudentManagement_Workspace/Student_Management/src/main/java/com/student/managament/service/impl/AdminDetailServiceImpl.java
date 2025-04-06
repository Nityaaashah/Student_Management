package com.student.managament.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.student.managament.entity.Admin;
import com.student.managament.repository.AdminRepository;

@Service
public class AdminDetailServiceImpl implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      
        System.out.println("Inside Load()>>>"+username);
    	Admin admin = adminRepository.findByUsername(username)
          
        		.orElseThrow(() -> new UsernameNotFoundException("Admin not found"));

        System.out.println("admin>>>"+admin);
        return User.withUsername(admin.getUsername())
                   .password(admin.getPassword())
                   .roles("ADMIN")
                   .build();
    }
}
