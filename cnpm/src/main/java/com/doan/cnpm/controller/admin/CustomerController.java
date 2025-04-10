package com.doan.cnpm.controller.admin;

import com.doan.cnpm.dto.response.CustomerDetailResponseDTO;
import com.doan.cnpm.dto.response.ListCustomerResponseDTO;
import com.doan.cnpm.entity.Customer;
import com.doan.cnpm.repository.CustomerRepository;
import com.doan.cnpm.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/listCustomer")
    public List<ListCustomerResponseDTO> getAllCustomers() {
        return customerService.listCustomers();
    }

    @GetMapping("/detailCustomer/{customerID}")
    public CustomerDetailResponseDTO getCustomerDetail(@PathVariable("customerID") Long customerID) {
        return customerService.detailCustomer(customerID);
    }
}
