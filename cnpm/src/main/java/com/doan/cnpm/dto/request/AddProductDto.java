package com.doan.cnpm.dto.request;


import com.doan.cnpm.entity.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddProductDto {
    private String name;
    private String description;
    private String material;
    private String brand;
    private String type;
    private int quantityImport;
    private double priceImport;
    private double priceSelling;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Gender {
        man, woman, other
    }

    public enum Status {
        in_stock, out_of_stock
    }
}
