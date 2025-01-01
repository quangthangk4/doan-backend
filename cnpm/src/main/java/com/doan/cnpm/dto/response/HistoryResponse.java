package com.doan.cnpm.dto.response;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoryResponse {
    private String name;
    private String brand;
    private String material;
    private double price_selling;
    private Long product_id;
    private Long quantity;
    private double total_price;
    private String image;
}
