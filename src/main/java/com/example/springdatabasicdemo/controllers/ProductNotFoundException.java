package com.example.springdatabasicdemo.controllers;

class ProductNotFoundException extends RuntimeException {
    ProductNotFoundException(Integer id) {
        super("Could not find product " + id);
    }
}