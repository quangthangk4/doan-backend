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
<<<<<<< HEAD
@CrossOrigin(origins = "http://localhost:3000")
=======
@CrossOrigin(origins = "*")
>>>>>>> 62b2f7a836d950e9823bd8aaf07bd41b95a9eb60
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
