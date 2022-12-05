package com.example.loginjpa.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.bytebuddy.asm.Advice;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@Getter
public enum ErrorCode {
    USERNAME_DUPLICATED(HttpStatus.CONFLICT, ""),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, ""),
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND, "");

    private HttpStatus httpStatus;

    private String message;
}
