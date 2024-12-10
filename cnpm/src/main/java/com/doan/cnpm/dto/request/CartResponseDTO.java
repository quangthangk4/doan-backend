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
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private double price_selling;
    private Long quantity;
    private String image;

    public enum Gender {
        man, woman, other
    }
}