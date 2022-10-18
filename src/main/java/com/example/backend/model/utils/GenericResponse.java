package com.example.backend.model.utils;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@ToString
@Getter
@Setter
@NoArgsConstructor
public class GenericResponse<T> {
    private T data;
    private HttpStatus status;
    private String message;
    private Long responseTime;
    private String dateTime;

    public GenericResponse<T> success(T data, String msg) {
        this.data = data;
        this.status = HttpStatus.OK;
        responseTime = System.currentTimeMillis();
        dateTime = LocalDateTime.now().toString();
        message = msg;
        return this;
    }
    public GenericResponse<T> error(T data, String msg, HttpStatus status) {
        this.data = data;
        this.status = status;
        responseTime = System.currentTimeMillis();
        dateTime = LocalDateTime.now().toString();
        message = msg;
        return this;
    }
    public GenericResponse<T> error(T data, Exception ex) {
        this.data = null;
        this.status = HttpStatus.BAD_REQUEST;
        responseTime = System.currentTimeMillis();
        dateTime = LocalDateTime.now().toString();
        message = ex.getMessage();
        return this;
    }
}
