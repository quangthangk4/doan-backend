package com.doan.cnpm.mapper;

import com.doan.cnpm.dto.response.TotalSaleTodayResponseDTO;
import org.mapstruct.Mapper;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface TotalSaleTodayMapper {
    default TotalSaleTodayResponseDTO toTotalSaleTodayResponseDTO(Object[] order) {
        if (order == null || order.length < 3) {
            return null;
        }

        return TotalSaleTodayResponseDTO.builder()
                .total((Double) order[0])
                .customerCount((Long) order[1])
                .orderCount((Long) order[2])
                .build();
    }
}
