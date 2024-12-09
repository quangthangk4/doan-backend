package com.doan.cnpm.controller;

import com.doan.cnpm.dto.response.ProductManResponse;
import com.doan.cnpm.entity.Product;
import com.doan.cnpm.repository.ProductRepository;
import com.doan.cnpm.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @GetMapping
    private List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        products = productRepository.findAll();
        return products;
    }

//    @GetMapping("/man")
//    private List<ProductManResponse> getManProducts() {
//        List<ProductManResponse> productManResponses = productService.getAllProductsMan();
//        return productManResponses;
//    }

}
