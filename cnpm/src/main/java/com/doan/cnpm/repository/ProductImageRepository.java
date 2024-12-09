package com.doan.cnpm.repository;

import com.doan.cnpm.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    @Query(value = "select i.imageurl from product_image i where i.productid =:productid", nativeQuery = true)
    List<String> findProductImageByID(@Param("productid") Long productid);
}
