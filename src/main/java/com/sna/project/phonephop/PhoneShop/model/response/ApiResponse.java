package com.sna.project.phonephop.PhoneShop.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ApiResponse <T>{
    private String message;
    private HttpStatus status;
    private T payload;
    private LocalDateTime timestamp;

}
