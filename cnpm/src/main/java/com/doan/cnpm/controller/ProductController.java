package com.doan.cnpm.controller;

import com.doan.cnpm.dto.response.AllProductResponse;
import com.doan.cnpm.dto.response.ProductDetailResponseDTO;
import com.doan.cnpm.entity.Product;
import com.doan.cnpm.entity.ProductImage;
import com.doan.cnpm.entity.Rating;
import com.doan.cnpm.repository.ProductRepository;
import com.doan.cnpm.repository.RatingRepository;
import com.doan.cnpm.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/man")
    public List<AllProductResponse> getProductMan(){
        return productService.getProductsMan();
    }

    @GetMapping("/woman")
    public List<AllProductResponse> getProductWoman(){
        return productService.getProductsWoman();
    }

    @GetMapping("/all")
    public List<AllProductResponse> getAllProduct(){
        return productService.getAllProducts();
    }

    @GetMapping("/detail/{productID}")
    public ProductDetailResponseDTO getProductById(@PathVariable("productID") Long productID){
        return productService.getProductsDetail(productID);
    }
}
