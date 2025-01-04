package com.doan.cnpm.dto.request;

import com.doan.cnpm.entity.Product;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponseDTO {
    private Long productId;
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Double totalPrice;
    private Long quantity;
    private Double price_selling;
    private String image;

    public enum Gender {
        man, woman, other
    }
}