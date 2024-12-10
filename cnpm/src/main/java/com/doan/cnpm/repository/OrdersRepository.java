package com.doan.cnpm.repository;

import com.doan.cnpm.entity.Customer;
import com.doan.cnpm.entity.Orders;
import org.hibernate.query.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    @Query(value = "select * from orders o where o.customerid = :customerID and o.status = 'pending'", nativeQuery = true)
    Optional<Orders> findOrdersByCustomerID(@Param("customerID") Long customerID);
}
