package com.doan.cnpm.controller.global;

import com.doan.cnpm.dto.request.ApiResponse;
import com.doan.cnpm.dto.request.AuthenticationRequest;
import com.doan.cnpm.dto.request.IntrospectRequest;
import com.doan.cnpm.dto.request.UserCreateDto;
import com.doan.cnpm.dto.response.AuthenticationResponse;
import com.doan.cnpm.dto.response.CustomerResponse;
import com.doan.cnpm.dto.response.IntrospectResponse;
import com.doan.cnpm.services.AuthenticationService;
import com.doan.cnpm.services.CustomerService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/global/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final CustomerService customerService;

    @PostMapping("/sign")
    ApiResponse<CustomerResponse> createCustomer(@RequestBody UserCreateDto userCreateDto) {
        return ApiResponse.<CustomerResponse>builder()
                .result(customerService.createCustomer(userCreateDto))
                .build();
    }

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        var result = authenticationService.authenticate(authenticationRequest);
        return ApiResponse.<AuthenticationResponse>builder()
                .code(200)
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest introspectRequest)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(introspectRequest);
        return ApiResponse.<IntrospectResponse>builder()
                .code(200)
                .result(result)
                .build();
    }
}
