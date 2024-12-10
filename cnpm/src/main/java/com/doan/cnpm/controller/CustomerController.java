package com.doan.cnpm.controller;

import com.doan.cnpm.entity.Customer;
import com.doan.cnpm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customer")
    public Optional<Customer> getCustomer() {
        return customerRepository.findById(1L);
    }
}
