package com.doan.cnpm.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TotalSaleTodayResponseDTO {
    private Double total;
    private Long customerCount;
    private Long orderCount;
}
