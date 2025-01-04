package com.doan.cnpm.controller.admin;

import com.doan.cnpm.dto.response.DetailOrderResponseDTO;
import com.doan.cnpm.dto.response.ListOrderResponseDTO;
import com.doan.cnpm.dto.response.TotalSaleTodayResponseDTO;
import com.doan.cnpm.repository.OrdersRepository;
import com.doan.cnpm.services.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
<<<<<<< HEAD
@CrossOrigin(origins = "http://localhost:3000")
=======
@CrossOrigin(origins = "*")
>>>>>>> 62b2f7a836d950e9823bd8aaf07bd41b95a9eb60
public class OrderController {
    private final OrdersRepository ordersRepository;
    private final OrdersService ordersService;

//    doanh thu hôm nay
    @GetMapping("/totalSalesToday")
    public TotalSaleTodayResponseDTO totalSalesToday() {
        return ordersService.totalSalesToday();
    }

    @GetMapping("/allorder")
    public List<ListOrderResponseDTO> getAllOrders() {
        return ordersService.listOrders();
    }

//    danh sách sản phẩm có trong order cho admin
    @GetMapping("/allitem/{orderid}")
    public DetailOrderResponseDTO getProductInOrderForAdmin(@PathVariable("orderid") Long orderid) {
        return ordersService.getDetailOrder(orderid);
    }
}
