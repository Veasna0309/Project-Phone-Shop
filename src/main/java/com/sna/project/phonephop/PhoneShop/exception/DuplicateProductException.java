package com.sna.project.phonephop.PhoneShop.exception;

import org.springframework.http.HttpStatus;

public class DuplicateProductException extends RuntimeException {
    public DuplicateProductException(String message) {
           super(message);
    }
}