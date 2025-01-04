package com.doan.cnpm.repository;

import com.doan.cnpm.dto.response.TotalSaleTodayResponseDTO;
import com.doan.cnpm.entity.Customer;
import com.doan.cnpm.entity.Orders;
import org.hibernate.query.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    @Query(value = "select * from orders o where o.customerid = :customerID and o.status = 'pending'", nativeQuery = true)
    Optional<Orders> findOrdersByCustomerID(@Param("customerID") Long customerID);

    @Query(value = "SELECT p.productid ,p.name, p.gender, o.total_price , i.quantity,(p.price_selling * i.quantity) as price, " +
            "(SELECT i.imageurl FROM product_image i WHERE i.productid = p.productid LIMIT 1) as imageUrl " +
            "FROM orders o " +
            "LEFT JOIN include i ON o.orderid = i.orderid LEFT JOIN product p ON p.productid = i.productid " +
            "WHERE o.customerid = ?1 and o.status = 'pending' ",
            nativeQuery = true)
    List<Object[]> findAllProductCart(Long customerID);


    @Query(value = "SELECT * FROM orders o " +
            "WHERE o.customerid =:customerID and o.status = 'pending' " , nativeQuery = true)
    Optional<Orders> findOrderByCustomerIDAndStatusPending(@Param("customerID") Long customerID);

    @Query(value = "select sum(o.total_price),count(o.customerid), count(orderid) " +
            "from orders o \n" +
            "where o.status = 'completed' and o.date = :today " +
            "group by o.status ", nativeQuery = true)
    Object[] totalPriceSellingToday(@Param("today") LocalDate today);

//   danh sách đơn hàng cho admin
    @Query(value = "select o.orderid, o.date, concat(c.first_name, ' ',c.last_name), o.status, o.total_price \n" +
            "from orders o left join customer c on o.customerid = c.customerid\n " +
            "where o.status = 'completed'" +
            "order by o.date desc;" , nativeQuery = true)
    List<Object[]> listOrdersForAdmin();

//    danh sách sản phẩm trong order đó,(tìm theo orderid)
    @Query(value = "SELECT p.name, p.brand, p.material, p.price_selling, o.status, i.quantity, " +
            "(p.price_selling * i.quantity) AS total_price, " +
            "(SELECT pi.imageurl FROM product_image pi WHERE pi.productid = p.productid LIMIT 1) AS imageurl, " +
            "CONCAT(c.first_name, ' ', c.last_name) AS full_name, c.phone_number, c.email, o.shipping_address " +
            "FROM orders o " +
            "LEFT JOIN include i ON i.orderid = o.orderid " +
            "LEFT JOIN product p ON p.productid = i.productid " +
            "LEFT JOIN customer c ON c.customerid = o.customerid " +
            "WHERE o.orderid = :orderid and o.status = 'completed'", nativeQuery = true)
    List<Object[]> AllProductInOrder(@Param("orderid") Long orderid);


    @Query(value = "SELECT p.name, p.brand, p.material, p.price_selling,p.productid, i.quantity, " +
            "(p.price_selling * i.quantity) AS total_price, " +
            "(SELECT pi.imageurl FROM product_image pi WHERE pi.productid = p.productid LIMIT 1) AS imageurl " +
            "FROM orders o " +
            "LEFT JOIN include i ON i.orderid = o.orderid " +
            "LEFT JOIN product p ON p.productid = i.productid " +
            "LEFT JOIN customer c ON c.customerid = o.customerid " +
            "WHERE c.customerid = :customerId and o.status = 'completed'", nativeQuery = true)
    List<Object[]> getHistoryProductBuyed(@Param("customerId") Long customerId);

}
