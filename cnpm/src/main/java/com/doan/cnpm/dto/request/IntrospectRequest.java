package com.doan.cnpm.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IntrospectRequest {
    private String token;
}
