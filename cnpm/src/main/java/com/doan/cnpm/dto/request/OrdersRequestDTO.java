package com.doan.cnpm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersRequestDTO {
    private Long customerId;
    private Long productId;
    private Long quantity;
    private Date date;
}
