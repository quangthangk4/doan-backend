package com.doan.cnpm.dto.response;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyInfoResponse {
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String gender;
    private String phoneNumber;
    private String address;
    private String email;

    public enum Gender {
        man, woman, other
    }
}
