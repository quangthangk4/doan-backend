package com.doan.cnpm.controller.admin;

import com.doan.cnpm.dto.response.ProductResponseDTO;
import com.doan.cnpm.entity.Product;
import com.doan.cnpm.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin")
public class ProductAdminController {
    private final ProductService productService;

    @GetMapping("/listproduct")
    public List<ProductResponseDTO> listProduct() {
        return productService.getAllProductsForAdmin();
    }
}
