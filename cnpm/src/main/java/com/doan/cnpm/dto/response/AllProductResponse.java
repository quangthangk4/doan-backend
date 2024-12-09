package com.doan.cnpm.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllProductResponse {
    private Long productID;
    private String name;
    private double priceSelling;
    private Long ratingCount;
    private String imageUrl;
}
