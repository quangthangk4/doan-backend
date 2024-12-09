package com.doan.cnpm.controller;

import com.doan.cnpm.entity.Customer;
import com.doan.cnpm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customer")
    public Customer customer() {
        Customer customer = new Customer();
        customer = customerRepository.findAll().get(0);
        customer.setLastName("Thắng");
        return customer;

    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
