package com.doan.cnpm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {
    USER_EXISTED(1001,"user already existed"),
    UNAUTHENTICATED(1002,"unauthenticated user"),
    HISTORY_EMPTY(400,"history is empty"),
    PRODUCT_NOT_FOUND(400,"product not found"),
    ;

    private int code;
    private String message;
}
