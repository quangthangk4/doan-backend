package com.doan.cnpm.repository;

import com.doan.cnpm.dto.response.ProductDetailResponseDTO;
import com.doan.cnpm.dto.response.ProductResponseDTO;
import com.doan.cnpm.entity.Product;
import com.doan.cnpm.entity.ProductImage;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.SqlResultSetMappings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query(value = "SELECT p.productid as productID, p.name as name, p.price_selling as priceSelling, " +
            "COUNT(r.ratingid) as ratingCount,AVG(r.star), " +
            "(SELECT i.imageurl FROM product_image i WHERE i.productid = p.productid LIMIT 1) as imageUrl " +
            "FROM product p " +
            "LEFT JOIN rating r ON p.productid = r.productid " +
            "WHERE p.gender = 'man' "+
            "GROUP BY p.productid",
            nativeQuery = true)
    List<Object[]> findProductManResponse();


    @Query(value = "SELECT p.productid as productID, p.name as name, p.price_selling as priceSelling, " +
            "COUNT(r.ratingid) as ratingCount,AVG(r.star), " +
            "(SELECT i.imageurl FROM product_image i WHERE i.productid = p.productid LIMIT 1) as imageUrl " +
            "FROM product p " +
            "LEFT JOIN rating r ON p.productid = r.productid " +
            "WHERE p.gender = 'woman' "+
            "GROUP BY p.productid",
            nativeQuery = true)
    List<Object[]> findProductWomanResponse();


    @Query(value = "SELECT p.productid as productID, p.name as name, p.price_selling as priceSelling, " +
            "COUNT(r.ratingid) as ratingCount,AVG(r.star), " +
            "(SELECT i.imageurl FROM product_image i WHERE i.productid = p.productid LIMIT 1) as imageUrl " +
            "FROM product p " +
            "LEFT JOIN rating r ON p.productid = r.productid " +
            "GROUP BY p.productid",
            nativeQuery = true)
    List<Object[]> findProductAllResponse();

    @Query(value = "SELECT p.productid,p.name, p.description, p.material, p.price_selling, p.status, " +
            "COUNT(r.ratingid), COUNT(r.content),AVG(r.star) " +
            "FROM product p " +
            "LEFT JOIN rating r ON p.productid = r.productid " +
            "WHERE p.productid = :productid " +
            "GROUP BY p.productid", nativeQuery = true)
    Object[] findProductDetails(@Param("productid") Long productid);

    @Query("SELECT new com.doan.cnpm.dto.response.ProductResponseDTO" +
            "(p.productID ,p.name ,p.description ," +
            "p.material ,p.brand ,p.quantitySold ,p.type ,p.quantityImport ," +
            "p.priceImport ,p.priceSelling ,p.quantityStock ,p.gender ,p.status) from Product p" +
            " order by p.productID asc ")
    List<ProductResponseDTO> findAllProduct();
}
