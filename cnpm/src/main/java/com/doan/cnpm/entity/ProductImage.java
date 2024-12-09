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
@Table(name = "ProductImage")
public class ProductImage {

    @EmbeddedId
    private ProductImagePK productImagePK; // Sử dụng ProductImagePK làm khóa chính kết hợp

    @JsonBackReference
    @ManyToOne
    @MapsId("productID")  // Liên kết với khóa chính của Product
    @JoinColumn(name = "productID", referencedColumnName = "productID")
    private Product product;
}
