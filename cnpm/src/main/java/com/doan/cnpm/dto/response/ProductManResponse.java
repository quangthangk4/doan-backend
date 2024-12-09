package com.doan.cnpm.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductManResponse {
    private int productID;
    private String name;
    private double priceSelling;
    private Long ratingCount;
}
