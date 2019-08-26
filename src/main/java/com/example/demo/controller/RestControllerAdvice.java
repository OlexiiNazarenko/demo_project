package com.example.demo.controller;

import com.example.demo.model.CategoryNotFoundException;
import com.example.demo.model.OrderNotFoundException;
import com.example.demo.model.ProductNotFoundException;
import com.example.demo.service.ExceedProductStorageOrderException;
import com.example.demo.service.ZeroProductQuantityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestControllerAdvice {

    @ResponseBody
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String productNotFoundHandler(ProductNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String categoryNotFoundHandler(CategoryNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String orderNotFoundHandler(OrderNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ExceedProductStorageOrderException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public String notEnoughProductHandler(ExceedProductStorageOrderException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ZeroProductQuantityException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public String zeroProductQuantityHandler(ZeroProductQuantityException e) {
        return e.getMessage();
    }
}
