package com.doan.cnpm.repository;

import com.doan.cnpm.entity.Include;
import com.doan.cnpm.entity.Orders;
import com.doan.cnpm.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncludeRepository extends JpaRepository<Include, Integer> {
    Optional<Include> findByOrders(Optional<Orders> orders);

    Optional<Include> findByOrdersAndProduct(Orders order, Product product);

    List<Include> findAllByOrders(Orders orders);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM include i WHERE i.orderid = :orderId AND i.productid = :productId", nativeQuery = true)
    void deleteItemInCart(@Param("orderId") Long orderId, @Param("productId") Long productId);
}
