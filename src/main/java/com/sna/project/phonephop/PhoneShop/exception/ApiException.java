package com.sna.project.phonephop.PhoneShop.exception;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Data
public class ApiException extends RuntimeException{
    private final HttpStatus status;
    private final String message;


}