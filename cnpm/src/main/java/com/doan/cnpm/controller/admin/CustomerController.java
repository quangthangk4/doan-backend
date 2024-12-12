package com.doan.cnpm.controller.admin;

import com.doan.cnpm.dto.response.ListCustomerResponseDTO;
import com.doan.cnpm.entity.Customer;
import com.doan.cnpm.repository.CustomerRepository;
import com.doan.cnpm.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/listcustomer")
    public List<ListCustomerResponseDTO> getAllCustomers() {
        return customerService.listCustomers();
    }

}
