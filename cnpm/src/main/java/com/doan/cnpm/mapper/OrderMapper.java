package com.doan.cnpm.mapper;

import com.doan.cnpm.dto.request.CartResponseDTO;
import com.doan.cnpm.dto.response.AllProductResponse;
import com.doan.cnpm.dto.response.ListOrderResponseDTO;
import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    default CartResponseDTO toCartResponseDTO(Object[] product) {
        if (product == null) {
            return null;
        }

        return CartResponseDTO.builder()
                .productId((Long) product[0])
                .name((String) product[1])
                .gender(CartResponseDTO.Gender.valueOf((String) product[2]))
                .totalPrice((Double) product[3])
                .quantity(((Number) product[4]).longValue())
                .price_selling((Double) product[5])
                .image((String) product[6])
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

    default ListOrderResponseDTO toListOrderResponseDTO(Object[] order) {
        if (order == null) {
            return null;
        }

        return ListOrderResponseDTO.builder()
                .orderid(((Number) order[0]).longValue())
                .date(((java.sql.Date) order[1]).toLocalDate())
                .full_name((String) order[2])
                .status(ListOrderResponseDTO.Status.valueOf((String) order[3]))
                .total_price(((Number) order[4]).doubleValue())
                .build();
    }

    default List<ListOrderResponseDTO> toListOrderResponseDTOs(List<Object[]> orders) {
        if (orders == null) {
            return null;
        }

        return orders.stream()
                .map(this::toListOrderResponseDTO)
                .collect(Collectors.toList());
    }
}
