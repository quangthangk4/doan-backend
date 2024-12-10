package com.doan.cnpm.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Include")
public class Include {

    @EmbeddedId
    private IncludePK id; // Sử dụng IncludePK làm khóa chính kết hợp


    @ManyToOne
    @JsonBackReference
    @MapsId("orderID")
    @JoinColumn(name = "orderID", referencedColumnName = "orderID") // Khóa ngoại cho Orders
    private Orders orders;


    @ManyToOne
    @MapsId("productID")
    @JsonBackReference
    @JoinColumn(name = "productID", referencedColumnName = "productID") // Khóa ngoại cho Product
    private Product product;

    private Long quantity;
}