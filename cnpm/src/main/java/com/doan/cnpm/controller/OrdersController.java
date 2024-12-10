package com.doan.cnpm.controller;

import com.doan.cnpm.dto.request.OrdersRequestDTO;
import com.doan.cnpm.entity.Orders;
import com.doan.cnpm.repository.OrdersRepository;
import com.doan.cnpm.services.OrdersService;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @PostMapping()
    public ResponseEntity<String> createOrder(@RequestBody OrdersRequestDTO ordersRequestDTO) {
        try {
            // Gọi Service để tạo đơn hàng
            ordersService.AddToOrder(ordersRequestDTO);
            // Trả về thông báo thành công
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Order has been successfully added!");
        } catch (Exception e) {
            // Trường hợp lỗi, trả về thông báo lỗi
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("lỗi rồi cu: " + e.getMessage());
        }
    }
}
