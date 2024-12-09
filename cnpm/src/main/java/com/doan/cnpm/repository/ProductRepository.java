package com.doan.cnpm.repository;

import com.doan.cnpm.dto.response.ProductManResponse;
import com.doan.cnpm.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("""
    SELECT new com.doan.cnpm.dto.response.ProductManResponse(
        p.productID,
        p.name,
        p.priceSelling,
        COUNT(r.ratingID)
    )
    FROM Product p
    LEFT JOIN p.ratings r
    GROUP BY p.productID
""")
    List<ProductManResponse> findAllProductsManResponse();
}
