package com.doan.cnpm.mapper;

import com.doan.cnpm.dto.request.UserCreateDto;
import com.doan.cnpm.dto.response.CustomerDetailResponseDTO;
import com.doan.cnpm.dto.response.CustomerResponse;
import com.doan.cnpm.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(UserCreateDto userCreateDto);
    UserCreateDto toUserCreateDto(Customer customer);

    CustomerResponse toCustomerResponse(Customer customer);
}
