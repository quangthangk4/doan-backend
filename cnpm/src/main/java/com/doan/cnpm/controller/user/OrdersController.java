package com.doan.cnpm.controller.user;

import com.doan.cnpm.dto.request.CartResponseDTO;
import com.doan.cnpm.dto.request.OrdersRequestDTO;
import com.doan.cnpm.repository.IncludeRepository;
import com.doan.cnpm.services.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")  // Cho phép từ localhost:3000
public class OrdersController {
    private final OrdersService ordersService;
    private final IncludeRepository includeRepository;

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

    @GetMapping("/myCart")
    public ResponseEntity<List<CartResponseDTO>> getCart() {
        return ResponseEntity.ok(ordersService.ShoppingCart());
    }

    @PutMapping("/checkout")
    public ResponseEntity<String> updateCheckOut() {
        try {
            ordersService.orderCheckOut();
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Thanh Toán thành công, lần sau mua tiếp nha bà:))!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("lỗi rồi cu em ơ: " + e.getMessage());
        }
    }

    @DeleteMapping("/itemcart/{productID}")
    public ResponseEntity<String> deleteItemCart(@PathVariable Long productID) {
        try {
            ordersService.orderRemoveItem(productID);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Xóa sản phẩm thành công!!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("lỗi rồi cu em ơ: " + e.getMessage());
        }
    }

}
