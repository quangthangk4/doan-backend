package com.doan.cnpm.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListOrderResponseDTO {
    private Long orderid;
    private LocalDate date;
    private String full_name;
    @Enumerated(EnumType.STRING)
    private Status status;
    private double total_price;

    public enum Status {
        pending, completed
    }
}
