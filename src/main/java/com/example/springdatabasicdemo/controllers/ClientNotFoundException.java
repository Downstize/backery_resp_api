package com.example.springdatabasicdemo.controllers;

class ClientNotFoundException extends RuntimeException {
    ClientNotFoundException(Long id) {
        super("Could not find client " + id);
    }
}