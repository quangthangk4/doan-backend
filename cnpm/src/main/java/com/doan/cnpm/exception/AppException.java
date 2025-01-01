package com.doan.cnpm.exception;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class AppException extends RuntimeException {
    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    private ErrorCode errorCode;
}
