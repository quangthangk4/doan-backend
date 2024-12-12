package com.doan.cnpm.services;

import com.doan.cnpm.dto.response.ListCustomerResponseDTO;
import com.doan.cnpm.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

//    danh sách customer cho admin
    public List<ListCustomerResponseDTO> listCustomers() {
        return customerRepository.findAllCustomers();
    }
}
