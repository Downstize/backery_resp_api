package com.example.springdatabasicdemo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BackeryNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(BackeryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String bakeryNotFoundHandler(BackeryNotFoundException ex) {
        return ex.getMessage();
    }
}