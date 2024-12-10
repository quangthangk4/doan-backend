package com.doan.cnpm.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderID;

    private Date date;
    private double totalPrice;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String shippingAddress;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customerID", referencedColumnName = "customerID")
    private Customer customer;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "employeeID", referencedColumnName = "employeeID")
    private Employee employee;



    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Include> includes;

    // Getters and Setters

    public enum Status {
        pending, completed, cancelled
    }
}
