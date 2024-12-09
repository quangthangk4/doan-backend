package com.doan.cnpm.mapper;

import com.doan.cnpm.dto.response.AllProductResponse;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    default AllProductResponse toAllProductResponse(Object[] product) {
        if (product == null) {
            return null;
        }

        return AllProductResponse.builder()
                .productID(((Number) product[0]).longValue())
                .name((String) product[1])
                .priceSelling(((Number) product[2]).doubleValue())
                .ratingCount(((Number) product[3]).longValue())
                .imageUrl((String) product[4])
                .build();
    }

    default List<AllProductResponse> toAllProductResponses(List<Object[]> products) {
        if (products == null) {
            return null;
        }

        return products.stream()
                .map(this::toAllProductResponse)
                .collect(Collectors.toList());
    }
}
