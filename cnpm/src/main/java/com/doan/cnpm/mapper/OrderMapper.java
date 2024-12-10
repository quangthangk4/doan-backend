package com.doan.cnpm.mapper;

import com.doan.cnpm.dto.request.CartResponseDTO;
import com.doan.cnpm.dto.response.AllProductResponse;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    default CartResponseDTO toCartResponseDTO(Object[] product) {
        if (product == null) {
            return null;
        }

        return CartResponseDTO.builder()
                .name((String) product[0])
                .gender(CartResponseDTO.Gender.valueOf((String) product[1]))
                .price_selling(((Number) product[2]).doubleValue())
                .quantity(((Number) product[3]).longValue())
                .image((String) product[4])
                .build();
    }

    default List<CartResponseDTO> toCartResponseDTOs(List<Object[]> products) {
        if (products == null) {
            return null;
        }

        return products.stream()
                .map(this::toCartResponseDTO)
                .collect(Collectors.toList());
    }
}
