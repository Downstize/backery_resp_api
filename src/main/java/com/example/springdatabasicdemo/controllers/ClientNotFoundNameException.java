package com.example.springdatabasicdemo.controllers;

class ClientNotFoundNameException extends RuntimeException {
    ClientNotFoundNameException(String name) {
        super("Could not find client " + name);
    }
}