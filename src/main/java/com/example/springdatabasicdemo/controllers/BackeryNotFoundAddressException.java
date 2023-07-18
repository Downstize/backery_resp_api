package com.example.springdatabasicdemo.controllers;

class BackeryNotFoundAddressException extends RuntimeException {
    BackeryNotFoundAddressException(String address) {
        super("Could not find client address" + address);
    }
}