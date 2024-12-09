package com.doan.cnpm.services;

import com.doan.cnpm.dto.response.AllProductResponse;
import com.doan.cnpm.mapper.ProductMapper;
import com.doan.cnpm.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    public List<AllProductResponse> getProductsMan(){
        List<Object[]> list = productRepository.findProductManResponse();
        return productMapper.toAllProductResponses(list);
    }

    public List<AllProductResponse> getProductsWoman(){
        List<Object[]> list = productRepository.findProductWomanResponse();
        return productMapper.toAllProductResponses(list);
    }

    public List<AllProductResponse> getAllProducts(){
        List<Object[]> list = productRepository.findProductAllResponse();
        return productMapper.toAllProductResponses(list);
    }
}
