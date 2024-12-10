package com.doan.cnpm.repository;

import com.doan.cnpm.entity.Include;
import com.doan.cnpm.entity.Orders;
import com.doan.cnpm.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncludeRepository extends JpaRepository<Include, Integer> {
    Optional<Include> findByOrders(Optional<Orders> orders);

    Optional<Include> findByOrdersAndProduct(Orders order, Product product);

    List<Include> findAllByOrders(Orders orders);
}
