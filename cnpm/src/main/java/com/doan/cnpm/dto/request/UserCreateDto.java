package com.doan.cnpm.dto.request;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {
    private Long customerID;
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
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        customer, admin
    }

    public enum Gender {
        man, woman, other
    }
}
