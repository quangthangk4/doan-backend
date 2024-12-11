package com.doan.cnpm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeID;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String phoneNumber;
    private String city;
    private String district;
    private String street;
    private String email;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Orders> orders;

    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY)
    private List<Product> products;

    public enum Gender {
        man, woman, other
    }
}