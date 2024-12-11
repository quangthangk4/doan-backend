package com.doan.cnpm.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailOrderResponseDTO {
    private String full_name;
    private String phone_number;
    private String email;
    private String shipping_address;
    private List<ProductDTO> products;


    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductDTO {
        private String name;
        private String brand;
        private String material;
        private double price_selling;
        @Enumerated(EnumType.STRING)
        private Status status;
        private Long quantity;
        private double total_price;
        private String image;

        public enum Status {
            pending, completed
        }
    }
}
