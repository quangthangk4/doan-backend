package com.doan.cnpm.controller.admin;

import com.doan.cnpm.dto.response.ProductResponseDTO;
import com.doan.cnpm.entity.Product;
import com.doan.cnpm.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
<<<<<<< HEAD
@CrossOrigin(origins = "http://localhost:3000")
=======
@CrossOrigin(origins = "*")
>>>>>>> 62b2f7a836d950e9823bd8aaf07bd41b95a9eb60
public class ProductAdminController {
    private final ProductService productService;

    @GetMapping("/listProduct")
    public List<ProductResponseDTO> listProduct() {
        return productService.getAllProductsForAdmin();
    }
}
