package com.doan.cnpm.services;

import com.doan.cnpm.dto.response.ProductManResponse;
import com.doan.cnpm.entity.Product;
import com.doan.cnpm.mapper.ProductMapper;
import com.doan.cnpm.repository.ProductImageRepository;
import com.doan.cnpm.repository.ProductRepository;
import com.doan.cnpm.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


//    public List<ProductManResponse> getAllProductsMan() {
//        List<ProductManResponse> productManResponses = new ArrayList<>();
//        productManResponses = productRepository.findAllProductsManResponse();
//        return productManResponses;
//    }

}
