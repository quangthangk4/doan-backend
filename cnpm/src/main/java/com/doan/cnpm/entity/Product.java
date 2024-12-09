package com.doan.cnpm.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Status status;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "employeeID", nullable = false, referencedColumnName = "employeeID")
    private Employee employee;

    @JsonManagedReference
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Rating> ratings;

    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> images;

//    @JsonManagedReference
//    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    private List<Include> includes;

    public enum Gender {
        man, woman, other
    }

    public enum Status {
        in_stock, out_of_stock
    }
}