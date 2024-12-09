package com.doan.cnpm.mapper;

import com.doan.cnpm.dto.response.ProductManResponse;
import com.doan.cnpm.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductManResponse toProductManResponse(Product product);
    List<ProductManResponse> toProductManResponses(List<Product> products);
}
