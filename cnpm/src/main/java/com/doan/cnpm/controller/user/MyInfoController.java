package com.doan.cnpm.controller.user;

import com.doan.cnpm.dto.request.ApiResponse;
import com.doan.cnpm.dto.response.CustomerDetailResponseDTO;
import com.doan.cnpm.dto.response.HistoryResponse;
import com.doan.cnpm.dto.response.MyInfoResponse;
import com.doan.cnpm.entity.Customer;
import com.doan.cnpm.services.CustomerService;
import com.doan.cnpm.services.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class MyInfoController {
    private final CustomerService customerService;
    private final OrdersService ordersService;

    @GetMapping("/myInfo")
    public ApiResponse<MyInfoResponse> getMyInfo() {
        MyInfoResponse data = customerService.myInfoResponse();
        return ApiResponse.<MyInfoResponse>builder()
                .code(400)
                .result(data)
                .build();
    }

    @GetMapping("/history")
    public ApiResponse<List<HistoryResponse>> getHistory() {
        var data = ordersService.getHistoryProductOrder();
        return ApiResponse.<List<HistoryResponse>>builder()
                .code(200)
                .result(data)
                .build();
    }
}
