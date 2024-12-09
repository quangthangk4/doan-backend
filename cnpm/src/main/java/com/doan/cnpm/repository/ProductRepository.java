package com.doan.cnpm.repository;

import com.doan.cnpm.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT p.productid as productID, p.name as name, p.price_selling as priceSelling, " +
            "COUNT(r.ratingid) as ratingCount, " +
            "(SELECT i.imageurl FROM product_image i WHERE i.productid = p.productid LIMIT 1) as imageUrl " +
            "FROM product p " +
            "LEFT JOIN rating r ON p.productid = r.productid " +
            "WHERE p.gender = 'man' "+
            "GROUP BY p.productid",
            nativeQuery = true)
    List<Object[]> findProductManResponse();


    @Query(value = "SELECT p.productid as productID, p.name as name, p.price_selling as priceSelling, " +
            "COUNT(r.ratingid) as ratingCount, " +
            "(SELECT i.imageurl FROM product_image i WHERE i.productid = p.productid LIMIT 1) as imageUrl " +
            "FROM product p " +
            "LEFT JOIN rating r ON p.productid = r.productid " +
            "WHERE p.gender = 'woman' "+
            "GROUP BY p.productid",
            nativeQuery = true)
    List<Object[]> findProductWomanResponse();


    @Query(value = "SELECT p.productid as productID, p.name as name, p.price_selling as priceSelling, " +
            "COUNT(r.ratingid) as ratingCount, " +
            "(SELECT i.imageurl FROM product_image i WHERE i.productid = p.productid LIMIT 1) as imageUrl " +
            "FROM product p " +
            "LEFT JOIN rating r ON p.productid = r.productid " +
            "GROUP BY p.productid",
            nativeQuery = true)
    List<Object[]> findProductAllResponse();
}
