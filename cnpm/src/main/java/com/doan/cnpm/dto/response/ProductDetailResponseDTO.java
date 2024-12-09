package com.doan.cnpm.dto.response;

import com.doan.cnpm.entity.ProductImage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDetailResponseDTO {
    private Long productID;
    private String name;
    private String description;
    private String material;
    private double priceSelling;

    @Enumerated(EnumType.STRING)
    private Status status;
    private Long ratingCount;
    private Long commentCount;
    private List<String> images;

    public enum Status {
        in_stock, out_of_stock
    }
}

