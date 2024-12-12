package com.doan.cnpm.repository;

import com.doan.cnpm.dto.response.CustomerDetailResponseDTO;
import com.doan.cnpm.dto.response.ListCustomerResponseDTO;
import com.doan.cnpm.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("SELECT new com.doan.cnpm.dto.response.ListCustomerResponseDTO" +
            "(c.customerID , concat(c.firstName, ' ', c.lastName), c.email " +
            ", c.phoneNumber, count(o.orderID), sum(o.totalPrice)) " +
            "from Customer c inner join Orders o on c.customerID = o.customer.customerID and o.status = 'completed' " +
            "group by c.customerID")
    List<ListCustomerResponseDTO> findAllCustomers();


    @Query(value = "select c.customerid, concat(c.first_name,' ',c.last_name),c.date_of_birth,c.gender," +
            "c.phone_number, concat(c.street,' ',c.district,' ',c.city),c.email,sum(o.total_price), " +
            "o.orderid, o.total_price, o.date, o.status " +
            "from customer c left join orders o on c.customerid = o.customerid " +
            "where c.customerid = :customerid group by c.customerid, o.orderid " +
            "order by o.date desc;", nativeQuery = true)
    List<Object[]> findCustomerDetailById(@Param("customerid") Long customerid);
}
