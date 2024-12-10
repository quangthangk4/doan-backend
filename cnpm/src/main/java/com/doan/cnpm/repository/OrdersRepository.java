package com.doan.cnpm.repository;

import com.doan.cnpm.entity.Customer;
import com.doan.cnpm.entity.Orders;
import org.hibernate.query.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    @Query(value = "select * from orders o where o.customerid = :customerID and o.status = 'pending'", nativeQuery = true)
    Optional<Orders> findOrdersByCustomerID(@Param("customerID") Long customerID);

    @Query(value = "SELECT p.name, p.gender, o.total_price , i.quantity, " +
            "(SELECT i.imageurl FROM product_image i WHERE i.productid = p.productid LIMIT 1) as imageUrl " +
            "FROM orders o " +
            "LEFT JOIN include i ON o.orderid = i.orderid LEFT JOIN product p ON p.productid = i.productid " +
            "WHERE o.customerid = ?1 and o.status = 'pending' ",
            nativeQuery = true)
    List<Object[]> findAllProductCart(Long customerID);


    @Query(value = "SELECT * FROM orders o " +
            "WHERE o.customerid =:customerID and o.status = 'pending' " , nativeQuery = true)
    Optional<Orders> findOrderByCustomerIDAndStatusPending(@Param("customerID") Long customerID);
}
