package com.doan.cnpm.controller.admin;

import com.doan.cnpm.dto.response.TotalSaleTodayResponseDTO;
import com.doan.cnpm.repository.OrdersRepository;
import com.doan.cnpm.services.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class orderController {
    private final OrdersRepository ordersRepository;
    private final OrdersService ordersService;

//    doanh thu hôm nay
    @GetMapping("/totalSalesToday")
    public TotalSaleTodayResponseDTO totalSalesToday() {
        return ordersService.totalSalesToday();
    }
}
