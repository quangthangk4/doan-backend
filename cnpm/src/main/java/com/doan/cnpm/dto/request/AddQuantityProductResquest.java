package com.doan.cnpm.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddQuantityProductResquest {
    private Long productId;
    private int quantity;
}
