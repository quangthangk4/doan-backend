package com.doan.cnpm.services;

import com.doan.cnpm.dto.request.AddProductDto;
import com.doan.cnpm.dto.request.AddQuantityProductResquest;
import com.doan.cnpm.dto.response.AllProductResponse;
import com.doan.cnpm.dto.response.ProductDetailResponseDTO;
import com.doan.cnpm.dto.response.ProductResponseDTO;
import com.doan.cnpm.entity.Employee;
import com.doan.cnpm.entity.Product;
import com.doan.cnpm.exception.ErrorCode;
import com.doan.cnpm.mapper.ProductMapper;
import com.doan.cnpm.repository.EmployeeRepository;
import com.doan.cnpm.repository.ProductImageRepository;
import com.doan.cnpm.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductMapper productMapper;
    private final EmployeeRepository employeeRepository;

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

    public ProductDetailResponseDTO getProductsDetail(Long productId){
        List<String> images = productImageRepository.findProductImageByID(productId);
        Object[] product = productRepository.findProductDetails(productId);
        product = (Object[]) product[0];
        ProductDetailResponseDTO result = productMapper.toProductDetailResponseDTO(product, images);
        return result;
    }

    //    Trả về danh sách product ( trả về entity lun, khỏi DTO đi đỡ tạo mệt người)
    public List<ProductResponseDTO> getAllProductsForAdmin() {
        return productRepository.findAllProduct();

    }

    // thêm product cho admin
    public AddProductDto addProduct(AddProductDto addProductDto) {

        // gán ngẫu nhiên nhân viên cho sản phẩm
        Random random = new Random();
        long id = random.nextInt(10) + 1; // Tạo số từ 1 đến 10
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Product product = Product.builder()
                .name(addProductDto.getName())
                .description(addProductDto.getDescription())
                .material(addProductDto.getMaterial())
                .brand(addProductDto.getBrand())
                .quantitySold(0)
                .type(addProductDto.getType())
                .quantityImport(addProductDto.getQuantityImport())
                .priceImport(addProductDto.getPriceImport())
                .priceSelling(addProductDto.getPriceSelling())
                .quantityStock(addProductDto.getQuantityImport())
                .gender(Product.Gender.valueOf(String.valueOf(addProductDto.getGender())))
                .status(Product.Status.valueOf(String.valueOf(addProductDto.getStatus())))
                .employee(employee)
                .build();

        Product savedProduct = productRepository.save(product);


        return productMapper.toAddProductDto(savedProduct);
    }

    // thêm số lượng sp
    public String quantityProductResquest(AddQuantityProductResquest resquest) {
        Product product = productRepository.findById(resquest.getProductId()).orElseThrow(
                () -> new RuntimeException(ErrorCode.PRODUCT_NOT_FOUND.getMessage()));

        product.setQuantityImport(product.getQuantityImport() + resquest.getQuantity());
        product.setQuantityStock(product.getQuantityStock() + resquest.getQuantity());
        Product p = productRepository.save(product);

        return "Thêm số lượng thành công!";
    }

}
