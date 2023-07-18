package com.example.springdatabasicdemo.controllers;

class OrderNotFoundException extends RuntimeException {
    OrderNotFoundException(Integer id) {
        super("Could not find order " + id);
    }
}