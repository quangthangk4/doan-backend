package com.doan.cnpm.dto.response;

import lombok.*;

@Builder
@Value
public class ListCustomerResponseDTO {
    private Long customerID;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Long orderCount;
    private Double totalBuy;

    public ListCustomerResponseDTO(Long customerID, String fullName, String email, String phoneNumber, Long orderCount, Double totalBuy) {
        this.customerID = customerID;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.orderCount = orderCount;
        this.totalBuy = totalBuy;
    }
}