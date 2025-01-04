package com.doan.cnpm.controller.global;

import com.doan.cnpm.dto.response.AllProductResponse;
import com.doan.cnpm.dto.response.ProductDetailResponseDTO;
import com.doan.cnpm.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/global/product")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductService productService;

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
