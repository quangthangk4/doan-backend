package com.doan.cnpm.mapper;

import com.doan.cnpm.dto.request.AddProductDto;
import com.doan.cnpm.dto.response.AllProductResponse;
import com.doan.cnpm.dto.response.ProductDetailResponseDTO;
import com.doan.cnpm.entity.Product;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    public AddProductDto toAddProductDto(Product product);

    default AllProductResponse toAllProductResponse(Object[] product) {
        if (product == null) {
            return null;
        }

        return AllProductResponse.builder()
                .productID(((Number) product[0]).longValue())
                .name((String) product[1])
                .priceSelling(((Number) product[2]).doubleValue())
                .ratingCount(((Number) product[3]).longValue())
                .ratingAverage(((BigDecimal) product[4]).doubleValue())
                .imageUrl((String) product[5])
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


    default ProductDetailResponseDTO toProductDetailResponseDTO(Object[] product,List<String> images) {
        if (product == null && images == null) {
            return null;
        }

        return ProductDetailResponseDTO.builder()
                .productID(((Number) product[0]).longValue())
                .name((String) product[1])
                .description((String) product[2])
                .material((String) product[3])
                .priceSelling(((Number) product[4]).doubleValue())
                .status(ProductDetailResponseDTO.Status.valueOf((String) product[5]))
                .ratingCount(((Number) product[6]).longValue())
                .commentCount(((Number) product[7]).longValue())
                .ratingAverage(((BigDecimal) product[8]).doubleValue())
                .images(images)
                .build();
    }
}
