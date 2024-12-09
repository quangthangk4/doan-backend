package com.doan.cnpm.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingID;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;

    private String content;

    @Min(1)
    @Max(5)
    private int star;

    private Date date;

    // Getters and Setters
}
