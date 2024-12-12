package com.doan.cnpm.dto.response;

import com.doan.cnpm.entity.Account;
import com.doan.cnpm.entity.Customer;
import com.doan.cnpm.entity.Orders;
import com.doan.cnpm.entity.Rating;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDetailResponseDTO {
    private Long customerID;

    private String fullName;
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String phoneNumber;
    private String address;
    private String email;

    private Double totalBuy;

    private List<orderDTO> order;

    public enum Gender {
        man, woman, other
    }


    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class orderDTO{
        private Long orderID;
        private Double total_price;
        private LocalDate date;
        @Enumerated(EnumType.STRING)
        private Status status;

        public enum Status{
            pending, completed
        }


    }
}
