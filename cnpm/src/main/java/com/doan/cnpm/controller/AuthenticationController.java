package com.doan.cnpm.controller;

import com.doan.cnpm.dto.request.ApiResponse;
import com.doan.cnpm.dto.request.AuthenticationRequest;
import com.doan.cnpm.dto.request.IntrospectRequest;
import com.doan.cnpm.dto.request.UserCreateDto;
import com.doan.cnpm.dto.response.AuthenticationResponse;
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
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final CustomerService customerService;

    @PostMapping("/sigin")
    UserCreateDto createCustomer(@RequestBody UserCreateDto userCreateDto) {
        UserCreateDto user = customerService.createCustomer(userCreateDto);
        return user;
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
