package com.doan.cnpm.repository;

import com.doan.cnpm.dto.response.ListCustomerResponseDTO;
import com.doan.cnpm.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

}
