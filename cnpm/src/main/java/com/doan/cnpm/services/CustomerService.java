package com.doan.cnpm.services;

import com.doan.cnpm.dto.request.UserCreateDto;
import com.doan.cnpm.dto.response.CustomerDetailResponseDTO;
import com.doan.cnpm.dto.response.CustomerResponse;
import com.doan.cnpm.dto.response.DetailOrderResponseDTO;
import com.doan.cnpm.dto.response.ListCustomerResponseDTO;
import com.doan.cnpm.entity.Customer;
import com.doan.cnpm.enums.Role;
import com.doan.cnpm.mapper.CustomerMapper;
import com.doan.cnpm.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;


    // create customer
    public CustomerResponse createCustomer(UserCreateDto userCreateDto) {
        if (customerRepository.existsCustomerByEmail(userCreateDto.getEmail())) {
            throw new RuntimeException("user đã tồn tại, vui lòng dùng email khác!");
        }

        Customer customer = customerMapper.toCustomer(userCreateDto);
        customer.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.CUSTOMER.name());
        customer.setRoles(roles);

        customerRepository.save(customer);
        return customerMapper.toCustomerResponse(customer);
    }

    // update password customer

//    danh sách customer cho admin
    public List<ListCustomerResponseDTO> listCustomers() {
        return customerRepository.findAllCustomers();
    }

//    chi tiết khách hàng
    public CustomerDetailResponseDTO detailCustomer(Long customerId) {
        List<Object[]> results = customerRepository.findCustomerDetailById(customerId);

        // Tạo danh sách sản phẩm
        List<CustomerDetailResponseDTO.orderDTO> orderList = new ArrayList<>();
//        khởi tạo giá trị để tí nữa builder
        Long customerID = null;
        String fullName = null, gender = null, phoneNumber = null,address = null, email = null;
        LocalDate dateOfBirth = null;
        Double totalBuy = null;


        for (Object[] result : results) {
            // Ánh xạ từng sản phẩm
            CustomerDetailResponseDTO.orderDTO order = CustomerDetailResponseDTO.orderDTO.builder()
                    .orderID(((Number) result[8]).longValue())
                    .total_price(((Number) result[9]).doubleValue())
                    .date(((java.sql.Date) result[10]).toLocalDate())
                    .status(CustomerDetailResponseDTO.orderDTO.Status.valueOf((String) result[11]))
                    .build();

            orderList.add(order);

            // Chỉ cần ánh xạ thông tin khách hàng một lần
            if (fullName == null) {
                customerID = (Long) result[0];
                fullName = (String) result[1];
                dateOfBirth = ((java.sql.Date) result[2]).toLocalDate();
                gender = (String) result[3];
                phoneNumber = (String) result[4];
                address = (String) result[5];
                email = (String) result[6];
                totalBuy = (Double) result[7];
            }
        }

        // Tạo DTO cho chi tiết đơn hàng
        return CustomerDetailResponseDTO.builder()
                .customerID(customerID)
                .fullName(fullName)
                .dateOfBirth(dateOfBirth)
                .gender(CustomerDetailResponseDTO.Gender.valueOf(gender))
                .phoneNumber(phoneNumber)
                .address(address)
                .email(email)
                .totalBuy(totalBuy)
                .order(orderList)
                .build();
    }
}
