package com.doan.cnpm.controller.admin;

import com.doan.cnpm.dto.request.AddProductDto;
import com.doan.cnpm.dto.request.AddQuantityProductResquest;
import com.doan.cnpm.dto.request.ApiResponse;
import com.doan.cnpm.dto.response.AuthenticationResponse;
import com.doan.cnpm.dto.response.ProductResponseDTO;
import com.doan.cnpm.entity.Product;
import com.doan.cnpm.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class ProductAdminController {
    private final ProductService productService;

    @GetMapping("/listProduct")
    public List<ProductResponseDTO> listProduct() {
        return productService.getAllProductsForAdmin();
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestBody AddProductDto productDto) {
        try {
            productService.addProduct(productDto);
            return "Tạo sản phẩm thành công!!";
        }
        catch (Exception e) {
            return "Tạo không thành công, Vui lòng nhập lại!!";
        }
    }

    @PostMapping("/addQuantityProduct")
    public ApiResponse<String> addQuantityProduct(@RequestBody AddQuantityProductResquest quantityProductResquest) {
        String message = productService.quantityProductResquest(quantityProductResquest);
        return ApiResponse.<String>builder()
                .code(200)
                .result(message)
                .build();
    }
}
