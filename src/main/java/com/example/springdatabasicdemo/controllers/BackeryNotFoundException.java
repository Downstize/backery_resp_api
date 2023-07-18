package com.example.springdatabasicdemo.controllers;

public class BackeryNotFoundException extends RuntimeException {
    BackeryNotFoundException(Long id) {
        super("Could not find backery " + id);
    }
   
}