package com.sna.project.phonephop.PhoneShop.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getStatus(), e.getMessage());
        return ResponseEntity
                .status(e.getStatus())
                .body(errorResponse);
    }
    @ExceptionHandler(DuplicateProductException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateProductException(DuplicateProductException ex) {
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}