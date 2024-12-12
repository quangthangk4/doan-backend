package com.doan.cnpm.dto.response;

import com.doan.cnpm.entity.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class ProductResponseDTO {
    private Long productID;

    private String name;
    private String description;
    private String material;
    private String brand;
    private int quantitySold;
    private String type;
    private int quantityImport;
    private double priceImport;
    private double priceSelling;
    private int quantityStock;

    @Enumerated(EnumType.STRING)
    private Product.Gender gender;

    @Enumerated(EnumType.STRING)
    private Product.Status status;
}
