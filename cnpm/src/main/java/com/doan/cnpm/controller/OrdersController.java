package com.doan.cnpm.controller;

import com.doan.cnpm.dto.request.CartResponseDTO;
import com.doan.cnpm.dto.request.OrdersRequestDTO;
import com.doan.cnpm.repository.IncludeRepository;
import com.doan.cnpm.services.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private IncludeRepository includeRepository;

    @PostMapping("/addtocart")
    public ResponseEntity<String> createOrder(@RequestBody OrdersRequestDTO ordersRequestDTO) {
        try {
            // Gọi Service để tạo đơn hàng
            ordersService.AddToCart(ordersRequestDTO);
            // Trả về thông báo thành công
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Order has been successfully added!");
        } catch (Exception e) {
            // Trường hợp lỗi, trả về thông báo lỗi
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("lỗi rồi cu: " + e.getMessage());
        }
    }

    @GetMapping("/cart/{customerID}")
    public ResponseEntity<List<CartResponseDTO>> getCart(@PathVariable("customerID") Long customerID) {
        return ResponseEntity.ok(ordersService.ShoppingCart(customerID));
    }

    @PutMapping("/checkout/{customerID}")
    public ResponseEntity<String> updateCheckOut(@PathVariable("customerID") Long customerID) {
        try {
            ordersService.orderCheckOut(customerID);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Thanh Toán thành công, lần sau mua tiếp nha bà:))!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("lỗi rồi cu em ơ: " + e.getMessage());
        }
    }

    @DeleteMapping("/itemcart/{customerID}/{productID}")
    public ResponseEntity<String> deleteItemCart(@PathVariable Long customerID, @PathVariable Long productID) {
        try {
            ordersService.orderRemoveItem(customerID, productID);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Xóa sản phẩm thành công!!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("lỗi rồi cu em ơ: " + e.getMessage());
        }
    }
}
